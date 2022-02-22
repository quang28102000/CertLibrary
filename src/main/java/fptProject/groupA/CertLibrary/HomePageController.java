package fptProject.groupA.CertLibrary;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fptProject.groupA.CertLibrary.persistence.Course;
import fptProject.groupA.CertLibrary.service.CourseService;
import fptProject.groupA.CertLibrary.service.EmployeeService;

@RestController
@RequestMapping("/course")
public class HomePageController {

	public final EmployeeService employeeService;
	public final CourseService courseService;

	public HomePageController(EmployeeService employeeService, CourseService courseService) {
		this.employeeService = employeeService;
		this.courseService = courseService;
	}

	@GetMapping("/all")
	public ResponseEntity<List<Course>> getAllCourse() {
		List<Course> courses = courseService.findAllCourse();
		return new ResponseEntity<>(courses, HttpStatus.OK);
	};

	@GetMapping("/numberOfCourses")
	public ResponseEntity<Integer> getNumberOfCourses() {
		Integer numberOfCourses = courseService.numberOfCourses();
		return new ResponseEntity<>(numberOfCourses, HttpStatus.OK);
	};

	@GetMapping("/registeredCoursesByEmployee")
	public ResponseEntity<Integer> getRegisteredCoursesByEmployee() {
		Integer registeredCoursesByEmployee = employeeService.registeredCoursesByEmployee();
		return new ResponseEntity<>(registeredCoursesByEmployee, HttpStatus.OK);
	};

	@GetMapping("/unregisteredCoursesByEmployee")
	public ResponseEntity<Integer> getUnregisteredCoursesByEmployee() {
		Integer unregisteredCoursesByEmployee = employeeService.unregisteredCoursesByEmployee();
		return new ResponseEntity<>(unregisteredCoursesByEmployee, HttpStatus.OK);
	};

	@GetMapping("/completeCourseByEmployee")
	public ResponseEntity<Integer> getcompleteCoursesByEmployee() {
		Integer completeCoursesByEmployee = employeeService.completeCoursesByEmployee();
		return new ResponseEntity<>(completeCoursesByEmployee, HttpStatus.OK);
	};

	@GetMapping("/incompleteCourseByEmployee")
	public ResponseEntity<Integer> getIncompleteCourseByEmployee() {
		Integer incompleteCoursesByPeople = employeeService.incompleteCoursesByEmployee();
		return new ResponseEntity<>(incompleteCoursesByPeople, HttpStatus.OK);
	};

	@GetMapping("/numberOfEmployeesInLast7days")
	public ResponseEntity<Integer> getNumberOfEmployeesInLast7days() {
		Integer numberOfEmployeesInLast7days = employeeService.numberOfEmployeesInLast7days();
		return new ResponseEntity<>(numberOfEmployeesInLast7days, HttpStatus.OK);
	};

}
