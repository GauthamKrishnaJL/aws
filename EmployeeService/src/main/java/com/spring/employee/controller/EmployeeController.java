package com.spring.employee.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.employee.model.Employee;
import com.spring.employee.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService service;
	
	@RequestMapping(value="/getemployee", method=RequestMethod.GET)
	public List<Employee> getEmployeeInfo(){
		List<Employee> li=service.getEmployeeData();
		return li;
	}
	@RequestMapping(value="/postemployee", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
     public Employee employeePostCall(@RequestBody Employee employee) {
		if(Objects.isNull(employee.getId())||(employee.getId()==0)) {
			throw new IllegalArgumentException("Employee ID is invalid");
		}
		Employee employeeObj = service.getEmployeeDataBasedOnId(employee.getId());
		return employeeObj;
	}
	@RequestMapping(value="/putemployee", method=RequestMethod.PUT,consumes= MediaType.APPLICATION_JSON_VALUE)
	public String putEmployeeData(@RequestBody Employee em) {
		boolean status=service.addEmployeeToDb(em);
		if(status) {
			return "Employee Data Added";
		}else {
			return "Employee Data not Added";
		}
	}
	@RequestMapping(value="/deleteemployee/{id}",method=RequestMethod.DELETE)
	public String deleteEmployeeData(@PathVariable("id")int id) {
		if(Objects.isNull(id)||id==0) {
			throw new IllegalArgumentException("ID is required");
		}
		boolean status=service.deleteEmployeeFromDb(id);
		if(status) {
			return "Employee Data Deleted";
		}else {
			return "Employee Data not Deleted";
		}
	}
	
}
