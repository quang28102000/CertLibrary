package groupA.certLibrary.CertLibrary;

import groupA.certLibrary.CertLibrary.model.Course;
import groupA.certLibrary.CertLibrary.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseResource {

	public final CourseService courseService;

	public CourseResource(CourseService courseService) {
		this.courseService = courseService;
	}

	@GetMapping("/all")
	public ResponseEntity<List<Course>> getAllCourse() {
		List<Course> courses = courseService.findAllCourse();
		System.out.println(courses);
		return new ResponseEntity<>(courses, HttpStatus.OK);
	};

	@GetMapping("/registeredCoursesByEmployee")
	public ResponseEntity<Integer> getRegisteredCoursesByEmployee() {
		Integer registeredCoursesByEmployee = courseService.registeredCoursesByEmployee();
		System.out.println(registeredCoursesByEmployee);
		return new ResponseEntity<>(registeredCoursesByEmployee, HttpStatus.OK);
	};

	@GetMapping("/unregisteredCoursesByEmployee")
	public ResponseEntity<Integer> getUnregisteredCoursesByEmployee() {
		Integer unregisteredCoursesByEmployee = courseService.unregisteredCoursesByEmployee();
		System.out.println(unregisteredCoursesByEmployee);
		return new ResponseEntity<>(unregisteredCoursesByEmployee, HttpStatus.OK);
	};

	@GetMapping("/completeCourseByEmployee")
	public ResponseEntity<Integer> getcompleteCoursesByEmployee() {
		Integer completeCoursesByEmployee = courseService.completeCoursesByEmployee();
		System.out.println(completeCoursesByEmployee);
		return new ResponseEntity<>(completeCoursesByEmployee, HttpStatus.OK);
	};

	@GetMapping("/incompleteCourseByEmployee")
	public ResponseEntity<Integer> getIncompleteCourseByEmployee() {
		Integer incompleteCoursesByPeople = courseService.incompleteCoursesByEmployee();
		System.out.println(incompleteCoursesByPeople);
		return new ResponseEntity<>(incompleteCoursesByPeople, HttpStatus.OK);
	};

	@GetMapping("/numberOfEmployeesInLast7days")
	public ResponseEntity<Integer> getNumberOfEmployeesInLast7days() {
		Integer numberOfEmployeesInLast7days = courseService.numberOfEmployeesInLast7days();
		System.out.println(numberOfEmployeesInLast7days);
		return new ResponseEntity<>(numberOfEmployeesInLast7days, HttpStatus.OK);
	};

	@GetMapping("/numberOfCourses")
	public ResponseEntity<Integer> getNumberOfCourses() {
		Integer numberOfCourses = courseService.numberOfCourses();
		System.out.println(numberOfCourses);
		return new ResponseEntity<>(numberOfCourses, HttpStatus.OK);
	};
	
}
