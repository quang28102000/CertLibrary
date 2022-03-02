package fptProject.groupA.CertLibrary.service;

import java.util.List;

import fptProject.groupA.CertLibrary.persistence.EmployeeDto;
import fptProject.groupA.CertLibrary.persistence.UserProfileDto;

public interface EmployeeService {
	List<EmployeeDto> getEmployees();
	UserProfileDto findEmployeeProfile(Integer id);
	List<EmployeeDto> findSubscribedEmployeesInLast7Days();
}
