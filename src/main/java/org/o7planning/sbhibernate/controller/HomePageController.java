package org.o7planning.sbhibernate.controller;

import java.util.List;

import org.o7planning.sbhibernate.presistence.Course;
import org.o7planning.sbhibernate.presistence.CourseDto;
import org.o7planning.sbhibernate.presistence.EmployeeDto;
import org.o7planning.sbhibernate.presistence.UserProfileDto;
import org.o7planning.sbhibernate.service.CourseService;
import org.o7planning.sbhibernate.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



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
		return new ResponseEntity<>(courses, HttpStatus.OK);
	};

	@GetMapping("/getEmployees")
	public ResponseEntity<List<EmployeeDto>> getEmployees() {
		List<EmployeeDto> getEmployees = employeeService.getEmployees();
		return new ResponseEntity<>(getEmployees, HttpStatus.OK);
	};

	@GetMapping("/userProfile/{id}")
	public ResponseEntity<UserProfileDto> getUserProfile(
			@PathVariable(name = "id", required = false) Integer id
	) {
		UserProfileDto userProfile = employeeService.findEmployeeProfile(id);
		return new ResponseEntity<UserProfileDto>(userProfile, HttpStatus.OK);
	}

	@GetMapping("/coursesDto")
	public ResponseEntity<List<CourseDto>> getCoursesDto() {
		List<CourseDto> courses = courseService.getCoursesDto();
		courses.forEach(c -> System.out.println(c));
		return new ResponseEntity<List<CourseDto>>(courses, HttpStatus.OK);
	}
	
	
}
