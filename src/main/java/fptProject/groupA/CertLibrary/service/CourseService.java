package fptProject.groupA.CertLibrary.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fptProject.groupA.CertLibrary.dao.CourseDao;
import fptProject.groupA.CertLibrary.persistence.Course;

@Service
@Transactional
public class CourseService {

	private final CourseDao courseDao;

	@Autowired
	public CourseService(CourseDao courseDao) {
		this.courseDao = courseDao;
	}

	public List<Course> findAllCourse() {
		return courseDao.findAll();
	}
	
	public Integer numberOfCourses() {
		return courseDao.numberOfCourses();
	}
	
}
