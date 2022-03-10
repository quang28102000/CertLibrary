package org.o7planning.sbhibernate.controller;

import java.util.List;

import org.o7planning.sbhibernate.presistence.CourseDto;
import org.o7planning.sbhibernate.presistence.InforPageDto;
import org.o7planning.sbhibernate.presistence.courseRegisterDto;
import org.o7planning.sbhibernate.service.InforPageService;
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

@RestController
@RequestMapping("/information")
public class InforPageController {
	
	@Autowired
	public InforPageService inforPageService;

	@GetMapping("/all")
	public ResponseEntity<List<InforPageDto>> getAllInformation() {
		List<InforPageDto> information = inforPageService.getInforDto();
		System.out.println(information.toString().trim());
		return new ResponseEntity<List<InforPageDto>>(information, HttpStatus.OK);
	}
	
	@PutMapping("/update")
    public ResponseEntity<InforPageDto> updateInfor(@RequestBody InforPageDto inforPageDto) {
    	InforPageDto dto = inforPageService.updateInfor(inforPageDto);
    	System.out.println(inforPageDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    
	@DeleteMapping("/delete")
    public ResponseEntity<InforPageDto> deleteInfor(@RequestBody String jsonText) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(jsonText);
        
        Integer courseId = jsonNode.get("courseEmployee").get("courseId").intValue();
		Integer employeeId = jsonNode.get("courseEmployee").get("employeeId").intValue();
		
//        System.err.println(courseId + "_" + employeeId);
		int flag = inforPageService.deleteInforDto(courseId, employeeId);
		return new ResponseEntity<>(HttpStatus.OK);
    }
	
	
//	@GetMapping("/delete")
//    public ResponseEntity<InforPageDto> deleteInfor(@PathVariable("id1") int course_id, @PathVariable("id2") int employee_id) {
//    	InforPageDto dto = inforPageService.updateInfor(inforPageDto);
//    	System.out.println(inforPageDto);
//        return new ResponseEntity<>(dto, HttpStatus.OK);
//    }
	
	
	
//    @PostMapping("/addCourse")
//    public ResponseEntity<CourseDto> addCourse(@RequestBody CourseDto courseDto) {
//    	CourseDto dto = courseService.addCourse(courseDto);
////    	System.out.println(courseDto);
//        return new ResponseEntity<>(dto, HttpStatus.CREATED);
//    }
}
