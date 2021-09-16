package com.spring.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.employee.dao.EmployeeDao;
import com.spring.employee.model.Employee;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeDao dao;
	public List<Employee> getEmployeeData(){
		List<Employee> emList=dao.getEmployeeData();
		return emList;
	}
	public Employee getEmployeeDataBasedOnId(int id) {
		List<Employee>emList=dao.getEmployeeDataBasedOnId(id);
		return emList.get(0);
	}
	public boolean addEmployeeToDb(Employee em) {
		boolean status=dao.addEmployeeToDb(em);
		return status;
	}
public boolean deleteEmployeeFromDb(int id) {
	boolean status=dao.deleteEmployeeFromDb(id);
	return status;
}
}
