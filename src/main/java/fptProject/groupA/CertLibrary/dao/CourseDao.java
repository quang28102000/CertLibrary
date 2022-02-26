package fptProject.groupA.CertLibrary.dao;

import java.util.List;

import fptProject.groupA.CertLibrary.persistence.Course;
import fptProject.groupA.CertLibrary.persistence.CourseDto;

public interface CourseDao {
	List<Course> getAll();
	Integer numberOfCourses();
	List<CourseDto> getCoursesDto();
}
