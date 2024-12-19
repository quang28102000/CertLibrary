package org.o7planning.sbhibernate.service;

import javax.transaction.Transactional;
import org.o7planning.sbhibernate.dao.addDAO;
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
