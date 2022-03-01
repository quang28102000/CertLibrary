package org.o7planning.sbhibernate.service;

import java.util.List;

import javax.transaction.Transactional;

import org.o7planning.sbhibernate.dao.CourseDao;
import org.o7planning.sbhibernate.presistence.Course;
import org.o7planning.sbhibernate.presistence.CourseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



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

	@Override
	public List<CourseDto> getCoursesDto() {
		return courseDao.getCoursesDto();
	}

}
