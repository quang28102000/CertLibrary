package fptProject.groupA.CertLibrary.controller;

import java.sql.Date;
import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import fptProject.groupA.CertLibrary.persistence.CourseEmployee;
import fptProject.groupA.CertLibrary.persistence.EmployeeCourseDto;
import fptProject.groupA.CertLibrary.service.CourseService;
import fptProject.groupA.CertLibrary.service.EmployeeService;

@RestController
@RequestMapping("/course-employee") 
public class CourseEmployeeController {
	
	@Autowired
	public EmployeeService employeeService;

	@Autowired
	public CourseService courseService;

	@GetMapping("/getCourseEmployees")
	public ResponseEntity<List<EmployeeCourseDto>> getEmployees() {
		List<EmployeeCourseDto> getEmployees = employeeService.getEmployees();
		return new ResponseEntity<>(getEmployees, HttpStatus.OK);
	};

	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteCourseEmployee(@RequestBody String jsonText)
			throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(jsonText);
		Integer courseId = node.get("course_id").asInt();
		Integer employeeId = node.get("employee_id").asInt();

		String noti = courseService.deleteCourseEmployeeWithFlag(courseId, employeeId);
		return new ResponseEntity<String>(noti, HttpStatus.OK);
	};

	@PutMapping("/update")
	public ResponseEntity<String> updateCourseEmployee(@RequestBody String jsonText)
			throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(jsonText);

		// Get Info
		Integer newCourseId = node.get("newId").get("course_id").asInt();
		Integer newEmployeeId = node.get("newId").get("employee_id").asInt();

		Integer oldCourseId = node.get("oldId").get("course_id").asInt();
		Integer oldEmployeeId = node.get("oldId").get("employee_id").asInt();

		String noti = "";

		if ((newCourseId == oldCourseId) && (newEmployeeId == oldEmployeeId)) {
			// TH1: id không thay đổi -> update idCourse và employeeId-> lấy id và save
			// những thông tin mới
			noti = courseService.updateCourseEmployee(new CourseEmployee(oldCourseId, oldEmployeeId,
					node.get("status").asInt(), mapper.convertValue(node.get("start_date"), Date.class),
					mapper.convertValue(node.get("end_date"), Date.class), node.get("certLink").asText(),
					node.get("isDeleted").asInt()));
		} else if ((newCourseId != oldCourseId) || (newEmployeeId != oldEmployeeId)) {
			// TH2: một trong 2 hoặc cả hai id đã thay đổi
			// -> xóa row của id cũ -> add id mới
			courseService.deleteCourseEmployee(oldCourseId, oldEmployeeId);
			courseService.addCourseEmployee(new CourseEmployee(newCourseId, newEmployeeId, node.get("status").asInt(),
					mapper.convertValue(node.get("start_date"), Date.class),
					mapper.convertValue(node.get("end_date"), Date.class), node.get("cert_link").asText(),
					node.get("is_deleted").asInt()));
		}

		return new ResponseEntity<String>(noti, HttpStatus.OK);
	};

	@DeleteMapping("/multipleDelete")
	public ResponseEntity<String> deleteMultipleCourseEmployee(@RequestBody String jsonText)
			throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(jsonText);

		Integer[] courseId = StreamSupport.stream(node.get("course_id").spliterator(), false)
				.map(jsonObj -> mapper.convertValue(jsonObj, Integer.class)).toArray(Integer[]::new);
		Integer[] employeeId = StreamSupport.stream(node.get("employee_id").spliterator(), true)
				.map(jsonObj -> mapper.convertValue(jsonObj, Integer.class)).toArray(Integer[]::new);

		System.out.println(courseId.toString());
		System.out.println(employeeId.toString());

		for (int i = 0; i < courseId.length; i++) {
			courseService.deleteCourseEmployeeWithFlag(courseId[i], employeeId[i]);
		}
		return new ResponseEntity<String>("multipleDelete succeeded!", HttpStatus.OK);
	};

}
