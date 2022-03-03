package org.o7planning.sbhibernate.service;

import java.util.List;

import javax.transaction.Transactional;

import org.o7planning.sbhibernate.dao.EmployeeDao;
import org.o7planning.sbhibernate.presistence.EmployeeDto;
import org.o7planning.sbhibernate.presistence.UserProfileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;

	@Override
	@Transactional
	public UserProfileDto findEmployeeProfile(Integer id) {
		return employeeDao.findEmployeeProfile(id);
	}

	@Override
	public List<EmployeeDto> getEmployees() {
		return employeeDao.getEmployees();
	}

}