package fptProject.groupA.CertLibrary.dao;

import java.util.List;

import fptProject.groupA.CertLibrary.persistence.EmployeeDto;
import fptProject.groupA.CertLibrary.persistence.UserProfileDto;

public interface EmployeeDao {
	List<EmployeeDto> getEmployees();
	UserProfileDto findEmployeeProfile(Integer id);
}
