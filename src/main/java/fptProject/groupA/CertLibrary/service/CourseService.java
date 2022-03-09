package fptProject.groupA.CertLibrary.service;

import java.util.List;

import fptProject.groupA.CertLibrary.persistence.Course;
import fptProject.groupA.CertLibrary.persistence.CourseDto;
import fptProject.groupA.CertLibrary.persistence.CourseEmployee;
import fptProject.groupA.CertLibrary.persistence.CourseHomePageDto;
import fptProject.groupA.CertLibrary.persistence.Employee;

public interface CourseService {
	List<Course> getAll();
	Integer numberOfCourses();
	List<CourseDto> getCoursesDto();
	List<CourseHomePageDto> getCoursesHomePageDto();
	Course addCourseForEmployee(Course course, Employee employee);
	CourseEmployee addCourseEmployee (CourseEmployee courseEmployee);

}
