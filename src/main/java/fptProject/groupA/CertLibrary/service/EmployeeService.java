package fptProject.groupA.CertLibrary.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fptProject.groupA.CertLibrary.dao.EmployeeDao;
import fptProject.groupA.CertLibrary.persistence.Employee;

@Service
@Transactional
public class EmployeeService {
	private final EmployeeDao employeeRepo;

	@Autowired
	public EmployeeService(EmployeeDao employeeRepo) {
		this.employeeRepo = employeeRepo;
	}

	public Employee addCourse(Employee employee) {
		return employeeRepo.save(employee);
	}

	public Integer registeredCoursesByEmployee() {
		return employeeRepo.registeredCoursesByEmployee();
	}

	public Integer unregisteredCoursesByEmployee() {
		return employeeRepo.unregisteredCoursesByEmployee();
	}

	public Integer completeCoursesByEmployee() {
		return employeeRepo.completeCoursesByEmployee();
	}

	public Integer incompleteCoursesByEmployee() {
		return employeeRepo.incompleteCoursesByEmployee();
	}

	public Integer numberOfEmployeesInLast7days() {
		return employeeRepo.numberOfEmployeesInLast7days();
	}

}