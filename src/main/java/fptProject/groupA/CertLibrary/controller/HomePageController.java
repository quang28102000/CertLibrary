package fptProject.groupA.CertLibrary.controller;

import java.lang.reflect.Array;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import fptProject.groupA.CertLibrary.persistence.Course;
import fptProject.groupA.CertLibrary.persistence.CourseDto;
import fptProject.groupA.CertLibrary.persistence.CourseEmployee;
import fptProject.groupA.CertLibrary.persistence.CourseHomePageDto;
import fptProject.groupA.CertLibrary.persistence.Employee;
import fptProject.groupA.CertLibrary.persistence.EmployeeCourseDto;
import fptProject.groupA.CertLibrary.persistence.EmployeeDto;
import fptProject.groupA.CertLibrary.persistence.UserProfileDto;
import fptProject.groupA.CertLibrary.service.CourseService;
import fptProject.groupA.CertLibrary.service.EmployeeService;

@RestController
@RequestMapping("/course")
public class HomePageController {

	@Autowired
	public EmployeeService employeeService;

	@Autowired
	public CourseService courseService;

	@GetMapping("/all")
	public ResponseEntity<List<Course>> getAllCourse() {
		List<Course> courses = courseService.getAll();
		// Trường hợp 1: Cập nhật khóa học mới cho một nhân viên thông qua việc 
		// update thông tim trong courseEmployee

		return new ResponseEntity<>(courses, HttpStatus.OK);
	};
	
	@GetMapping("/getEmployeesInfo")
	public ResponseEntity<List<EmployeeDto>> getAllEmployeesInfo() {
		List<EmployeeDto> employees = employeeService.getEmployeesInfo();
		employees.forEach(e -> System.out.println(e));
		return new ResponseEntity<>(employees, HttpStatus.OK);
	};

	@GetMapping("/getEmployees")
	public ResponseEntity<List<EmployeeCourseDto>> getEmployees() {
		List<EmployeeCourseDto> getEmployees = employeeService.getEmployees();
		return new ResponseEntity<>(getEmployees, HttpStatus.OK);
	};

	@GetMapping("/userProfile/{id}")
	public ResponseEntity<UserProfileDto> getUserProfile(
			@PathVariable(name = "id", required = false) Integer id
	) {
		UserProfileDto userProfile = employeeService.findEmployeeProfile(id);
		System.out.println(userProfile);
		return new ResponseEntity<UserProfileDto>(userProfile, HttpStatus.OK);
	}

	@GetMapping("/coursesDto")
	public ResponseEntity<List<CourseDto>> getCoursesDto() {
		List<CourseDto> courses = courseService.getCoursesDto();
		return new ResponseEntity<List<CourseDto>>(courses, HttpStatus.OK);
	}

	@GetMapping("/coursesHomePageDto")
	public ResponseEntity<List<CourseHomePageDto>> getCoursesHomePageDto() {
		List<CourseHomePageDto> courses = courseService.getCoursesHomePageDto();
		courses.forEach(c -> System.out.println(c));
		return new ResponseEntity<List<CourseHomePageDto>>(courses, HttpStatus.OK);
	}

	
	@GetMapping("/getEmployeesInLast7Days")
	public ResponseEntity<List<EmployeeCourseDto>> getEmployeesInLast7Days() {
		List<EmployeeCourseDto> employees = employeeService.findSubscribedEmployeesInLast7Days();
		return new ResponseEntity<List<EmployeeCourseDto>>(employees, HttpStatus.OK);
	}
	

	@PostMapping("/addCourseRegister")
	public ResponseEntity<CourseEmployee> addCourseRegisterForEmployee
					(@RequestBody String jsonText) throws JsonMappingException, JsonProcessingException {
//		CourseEmployee courseEmployee = new CourseEmployee(new Id(9, 1), 4,
//				LocalDateTime.parse("2022-03-01 00:00:00", DateTimeFormatter.ofPattern("YYYY-MM-DD hh:mm:ss")),
//				LocalDateTime.parse("2022-03-03 00:00:00", DateTimeFormatter.ofPattern("YYYY-MM-DD hh:mm:ss")),
//				"đây là Link cert của nhân viên mới được add vào khóa mới", 0);
//		System.out.println(course);
//		CourseEmployee theCourseEmployee = courseService.addCourseForEmployee(courseEmployee);
//		System.out.println(theCourseEmployee.toString());
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(jsonText);
//		
//		Integer courseId = node.get("courseEmployee").get("courseId").intValue();
//		Integer employeeId = node.get("courseEmployee").get("employeeId").intValue();
//		Id courseEmployeeId = node.get("courseEmployee").to;
		
//		Course theCourse = mapper.convertValue(node.get("course"), Course.class);
//		Employee theEmployee = mapper.convertValue(node.get("employee"), Employee.class);
		CourseEmployee theCourseEmployee = mapper.convertValue(node.get("courseEmployee"), CourseEmployee.class);
//		format("YYYY-MM-DD hh:mm:ss")
//		SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
//		theCourse.setId(courseId);
//		theEmployee.setId(employeeId);
//		theCourseEmployee.setCourseId(courseId);
//		theCourseEmployee.setEmployeeId(employeeId);
//		theCourseEmployee.setStartDate("2022-03-08 08:08:11");
//		courseService.addCourseForEmployee(theCourse, theEmployee);
		courseService.addCourseEmployee(theCourseEmployee);
//		System.out.println(node.get("startDate").toString());
		return new ResponseEntity<CourseEmployee>(theCourseEmployee, HttpStatus.CREATED);
	};
	
	@PostMapping("/addCourse")
	public ResponseEntity<Course> addCourse
					(@RequestBody String jsonText) throws JsonMappingException, JsonProcessingException {	
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(jsonText);
		
		// Course
		Integer courseId = node.get("course_id").asInt();
		String tittle = node.get("tittle").asText();
		String platform = node.get("platform").asText();
		String category = node.get("category").asText();
		
		// CourseDetail
		Integer courseLength = node.get("courseLength").asInt();
		String image = node.get("image").toString();
		
		Course theCourse = new Course(courseId, tittle, platform, category);
		
		// skills
		String[] skillsName = StreamSupport.stream(node.get("skills").get("skill_name").spliterator(), false)
											.map(jsonObj -> mapper.convertValue(jsonObj, String.class))
											.toArray(String[]::new);
		Integer[] skillsId = StreamSupport.stream(node.get("skills").get("skill_id").spliterator(), false)
											.map(jsonObj -> mapper.convertValue(jsonObj, Integer.class))
											.toArray(Integer[]::new);
		Integer skillFlag = node.get("skill_flag").asInt();
		
		String notiCourse = courseService.addCourse(theCourse);
		String notiCourseDetail = courseService.addCourseDetail(theCourse, courseLength, image);
		String notiSkill  = courseService.addSkillOfACourse(skillsId, skillsName, skillFlag);
		String notiCourseSkill = courseService.addCourseSkill(theCourse, skillsId);
		
		System.out.println(notiCourse);
		System.out.println(notiCourseDetail);
		System.out.println(notiSkill);
		System.out.println(notiCourseSkill);
		
		return new ResponseEntity<Course>(theCourse, HttpStatus.CREATED);
	};
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteCourseEmployee(@RequestBody String jsonText) throws JsonMappingException, JsonProcessingException {
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
		
		if((newCourseId == oldCourseId) && (newEmployeeId == oldEmployeeId)) {
			// TH1: id không thay đổi -> update idCourse và employeeId-> lấy id và save những thông tin mới
			noti = courseService.updateCourseEmployee(new CourseEmployee(oldCourseId, oldEmployeeId, node.get("status").asInt(),
					mapper.convertValue(node.get("start_date"), Date.class), 
					mapper.convertValue(node.get("end_date"), Date.class), 
					node.get("certLink").asText(), node.get("isDeleted").asInt()));
		} else if ((newCourseId != oldCourseId) || (newEmployeeId != oldEmployeeId)) {
			// TH2: một trong 2 hoặc cả hai id đã thay đổi -> xóa row của id cũ -> add id mới
			courseService.deleteCourseEmployee(oldCourseId, oldEmployeeId);
			courseService.addCourseEmployee(new CourseEmployee(newCourseId, newEmployeeId, node.get("status").asInt(),
					mapper.convertValue(node.get("start_date"), Date.class), 
					mapper.convertValue(node.get("end_date"), Date.class), 
					node.get("cert_link").asText(), 
					node.get("is_deleted").asInt()));
		}
		
		return new ResponseEntity<String>(noti, HttpStatus.OK);
	};
	
	@DeleteMapping("/multipleDelete")
	public ResponseEntity<String> deleteMultipleCourseEmployee(@RequestBody String jsonText) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(jsonText);
		
		Integer[] courseId = StreamSupport.stream(node.get("course_id").spliterator(), false)
								.map(jsonObj -> mapper.convertValue(jsonObj, Integer.class))
								.toArray(Integer[]::new);
		Integer[] employeeId = StreamSupport.stream(node.get("employee_id").spliterator(), true)
								.map(jsonObj -> mapper.convertValue(jsonObj, Integer.class)).toArray(Integer[]::new);
		
//		Integer[] courseId = mapper.convertValue(node.get("course_id"), Integer[].class);
//		Integer[] employeeId = mapper.convertValue(node.get("employee_id"), Integer[].class);
		System.out.println(courseId.toString());
		System.out.println(employeeId.toString());
		
		for(int i = 0; i < courseId.length; i++) {
			courseService.deleteCourseEmployeeWithFlag(courseId[i], employeeId[i]);
		}
		return new ResponseEntity<String>("multipleDelete succeeded!", HttpStatus.OK);
	};
	
}
