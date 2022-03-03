package org.o7planning.sbhibernate.service;

import java.util.List;

import org.o7planning.sbhibernate.presistence.Course;
import org.o7planning.sbhibernate.presistence.CourseDto;



public interface CourseService {
	List<Course> getAll();
	Integer numberOfCourses();
	List<CourseDto> getCoursesDto();
}
