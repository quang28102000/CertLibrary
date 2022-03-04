package fptProject.groupA.CertLibrary.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fptProject.groupA.CertLibrary.persistence.Course;
import fptProject.groupA.CertLibrary.persistence.CourseDto;
import fptProject.groupA.CertLibrary.persistence.CourseEmployee;
import fptProject.groupA.CertLibrary.persistence.CourseEmployee.Id;
import fptProject.groupA.CertLibrary.persistence.CourseHomePageDto;
import fptProject.groupA.CertLibrary.persistence.CourseRegisterDto;
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
	public ResponseEntity<List<EmployeeDto>> getEmployeesInLast7Days() {
		List<EmployeeDto> employees = employeeService.findSubscribedEmployeesInLast7Days();
		return new ResponseEntity<List<EmployeeDto>>(employees, HttpStatus.OK);
	}
	

	@PostMapping("/addCourseRegister")
	public ResponseEntity<CourseRegisterDto> addCourseRegisterForEmployee(@RequestBody CourseRegisterDto courseRegisterDto) {
//		CourseEmployee courseEmployee = new CourseEmployee(new Id(9, 1), 4,
//				LocalDateTime.parse("2022-03-01 00:00:00", DateTimeFormatter.ofPattern("YYYY-MM-DD hh:mm:ss")),
//				LocalDateTime.parse("2022-03-03 00:00:00", DateTimeFormatter.ofPattern("YYYY-MM-DD hh:mm:ss")),
//				"đây là Link cert của nhân viên mới được add vào khóa mới", 0);
		System.out.println(courseRegisterDto);
//		CourseEmployee theCourseEmployee = courseService.addCourseForEmployee(courseEmployee);
//		System.out.println(theCourseEmployee.toString());
		return new ResponseEntity<CourseRegisterDto>(courseRegisterDto, HttpStatus.CREATED);
	};
	
}
