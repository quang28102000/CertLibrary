package fptProject.groupA.CertLibrary.dao;

import java.util.List;

import fptProject.groupA.CertLibrary.persistence.EmployeeCourseDto;
import fptProject.groupA.CertLibrary.persistence.EmployeeDto;
import fptProject.groupA.CertLibrary.persistence.UserProfileDto;

public interface EmployeeDao {
	List<EmployeeCourseDto> getEmployees();
	List<EmployeeDto> getEmployeesInfo();
	UserProfileDto findEmployeeProfile(Integer id);
	List<EmployeeCourseDto> findSubscribedEmployeesInLast7Days();
}
