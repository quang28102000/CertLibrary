package fptProject.groupA.CertLibrary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fptProject.groupA.CertLibrary.persistence.Course;
import fptProject.groupA.CertLibrary.persistence.CourseDto;
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
		return new ResponseEntity<>(courses, HttpStatus.OK);
	};

	@GetMapping("/getEmployees")
	public ResponseEntity<List<EmployeeDto>> getEmployees() {
		List<EmployeeDto> getEmployees = employeeService.getEmployees();
		return new ResponseEntity<>(getEmployees, HttpStatus.OK);
	};

	@GetMapping("/userProfile")
	public ResponseEntity<UserProfileDto> getUserProfile() {
		UserProfileDto userProfile = employeeService.findEmployeeProfile();
		return new ResponseEntity<UserProfileDto>(userProfile, HttpStatus.OK);
	}

	@GetMapping("/coursesDto")
	public ResponseEntity<List<CourseDto>> getCoursesDto() {
		List<CourseDto> courses = courseService.getCoursesDto();
		courses.forEach(c -> System.out.println(c));
		return new ResponseEntity<List<CourseDto>>(courses, HttpStatus.OK);
	}
}
