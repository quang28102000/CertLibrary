package fptProject.groupA.CertLibrary.service;

import java.util.List;

import fptProject.groupA.CertLibrary.persistence.Course;
import fptProject.groupA.CertLibrary.persistence.CourseDto;

public interface CourseService {
	List<Course> getAll();
	Integer numberOfCourses();
	List<CourseDto> getCoursesDto();
}
