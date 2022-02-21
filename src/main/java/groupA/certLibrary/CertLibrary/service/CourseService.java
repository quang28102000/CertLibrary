package groupA.certLibrary.CertLibrary.service;

import groupA.certLibrary.CertLibrary.model.Course;
import groupA.certLibrary.CertLibrary.repo.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CourseService {
	private final CourseRepo courseRepo;

	@Autowired
	public CourseService(CourseRepo courseRepo) {
		this.courseRepo = courseRepo;
	}

	public Course addCourse(Course course) {
		return courseRepo.save(course);
	}

	public List<Course> findAllCourse() {
		return courseRepo.findAll();
	}

	public Integer registeredCoursesByEmployee() {
		return courseRepo.registeredCoursesByEmployee();
	}

	public Integer unregisteredCoursesByEmployee() {
		return courseRepo.unregisteredCoursesByEmployee();
	}

	public Integer completeCoursesByEmployee() {
		return courseRepo.completeCoursesByEmployee();
	}

	public Integer incompleteCoursesByEmployee() {
		return courseRepo.incompleteCoursesByEmployee();
	}
	
	public Integer numberOfEmployeesInLast7days() {
		return courseRepo.numberOfEmployeesInLast7days();
	}
	
	public Integer numberOfCourses() {
		return courseRepo.numberOfCourses();
	}
	

}