package fptProject.groupA.CertLibrary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fptProject.groupA.CertLibrary.persistence.EmployeeCourseDto;
import fptProject.groupA.CertLibrary.persistence.EmployeeDto;
import fptProject.groupA.CertLibrary.persistence.UserProfileDto;
import fptProject.groupA.CertLibrary.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	public EmployeeService employeeService;

	// OK
	@GetMapping("/getEmployeesInfo")
	public ResponseEntity<List<EmployeeDto>> getAllEmployeesInfo() {
		List<EmployeeDto> employees = employeeService.getEmployeesInfo();
		employees.forEach(e -> System.out.println(e));
		return new ResponseEntity<>(employees, HttpStatus.OK);
	};

	@GetMapping("/userProfile/{id}")
	public ResponseEntity<UserProfileDto> getUserProfile(@PathVariable(name = "id", required = false) Integer id) {
		UserProfileDto userProfile = employeeService.findEmployeeProfile(id);
		System.out.println(userProfile);
		return new ResponseEntity<UserProfileDto>(userProfile, HttpStatus.OK);
	}

	@GetMapping("/getEmployeesInLast7Days")
	public ResponseEntity<List<EmployeeCourseDto>> getEmployeesInLast7Days() {
		List<EmployeeCourseDto> employees = employeeService.findSubscribedEmployeesInLast7Days();
		return new ResponseEntity<List<EmployeeCourseDto>>(employees, HttpStatus.OK);
	}

}
