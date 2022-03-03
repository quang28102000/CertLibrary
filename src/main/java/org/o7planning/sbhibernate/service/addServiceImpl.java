package org.o7planning.sbhibernate.service;

import java.util.List;

import javax.transaction.Transactional;

import org.o7planning.sbhibernate.dao.CourseDao;
import org.o7planning.sbhibernate.dao.addDAO;
import org.o7planning.sbhibernate.presistence.Course;
import org.o7planning.sbhibernate.presistence.CourseDto;
import org.o7planning.sbhibernate.presistence.courseRegisterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
@Transactional
public class addServiceImpl implements addService {

	@Autowired
	private addDAO addDAO;

	
	@Override
	@Transactional
	public courseRegisterDto addCourseRegis(courseRegisterDto registerDto) {
		 
		 return addDAO.addNewCourse(registerDto);
	}

	

}
