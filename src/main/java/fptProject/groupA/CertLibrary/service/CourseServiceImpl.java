package fptProject.groupA.CertLibrary.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fptProject.groupA.CertLibrary.dao.CourseDao;
import fptProject.groupA.CertLibrary.persistence.Course;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDao courseDao;

	@Override
	@Transactional
	public List<Course> getAll() {
		return courseDao.getAll();
	}

	@Override
	@Transactional
	public Integer numberOfCourses() {
		return courseDao.numberOfCourses();
	}

}
