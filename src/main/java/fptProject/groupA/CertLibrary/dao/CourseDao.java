package fptProject.groupA.CertLibrary.dao;

import java.util.List;

import fptProject.groupA.CertLibrary.persistence.Course;

public interface CourseDao {

	List<Course> getAll();
	Integer numberOfCourses();
}
