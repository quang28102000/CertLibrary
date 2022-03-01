package org.o7planning.sbhibernate.service;

import java.util.List;

import org.o7planning.sbhibernate.presistence.EmployeeDto;
import org.o7planning.sbhibernate.presistence.UserProfileDto;



public interface EmployeeService {
	List<EmployeeDto> getEmployees();
	UserProfileDto findEmployeeProfile(Integer id);
}
