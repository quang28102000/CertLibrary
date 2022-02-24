package fptProject.groupA.CertLibrary.service;

import java.util.List;

import fptProject.groupA.CertLibrary.persistence.Course;

public interface CourseService {
	List<Course> getAll();
	Integer numberOfCourses();
}
