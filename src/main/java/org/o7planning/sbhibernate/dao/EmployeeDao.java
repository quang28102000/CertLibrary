package org.o7planning.sbhibernate.dao;

import java.util.List;

import org.o7planning.sbhibernate.presistence.EmployeeDto;
import org.o7planning.sbhibernate.presistence.UserProfileDto;



public interface EmployeeDao {
	List<EmployeeDto> getEmployees();
	UserProfileDto findEmployeeProfile(Integer id);
}
