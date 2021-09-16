package com.spring.employee.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.spring.employee.model.Employee;

@Component
public class EmployeeDao {
	@Autowired
	private JdbcTemplate template;
	public List<Employee> getEmployeeData(){
		String sql = "select * from employee";
		List<Employee> employeeList = template.query(sql, new ResultSetExtractor<List<Employee>>() {
			@Override
			public List<Employee> extractData(ResultSet rs) throws SQLException,DataAccessException{
				List<Employee> list = new ArrayList<>();
				while(rs.next()) {
					Employee employee = new Employee();
					employee.setId(rs.getInt("employeeid"));
					employee.setName(rs.getString("name"));
					employee.setAddress(rs.getString("address"));
					employee.setContact(rs.getString("contact"));
					employee.setProjectname(rs.getString("projectname"));
					employee.setManager(rs.getString("manager"));
					list.add(employee);

				}
				return list;
			}
		});
		return employeeList;

	}
	public List<Employee> getEmployeeDataBasedOnId(int id){
		String sql = "select * from employee where employeeid ="+id;
		List<Employee> employeeList=template.query(sql,new ResultSetExtractor<List<Employee>>() {
			@Override
			public List<Employee>extractData(ResultSet rs)throws SQLException,DataAccessException{
				List<Employee> list= new ArrayList();
				while(rs.next()) {
					Employee employee = new Employee();
					employee.setId(rs.getInt("employeeid"));
					employee.setName(rs.getString("name"));
					employee.setAddress(rs.getString("address"));
					employee.setContact(rs.getString("contact"));
					employee.setProjectname(rs.getString("projectname"));
					employee.setManager(rs.getString("manager"));
					list.add(employee);
				}
				return list;
			}
			
		});
		return employeeList;
	}
	public boolean addEmployeeToDb(Employee em) {
		boolean status=false;
		try {
			String sql="insert into employee(employeeid,name,address,contact,projectname,manager) values  ("+em.getId()+",'"+em.getName()+
					"','"+em.getAddress()+"',"+em.getContact()+",'"+em.getProjectname()+"','"+em.getManager()+"')";
			template.execute(sql);
			status=true;
		}catch (Exception e) {
			status=false;
		}
		return status;
		}
	public boolean deleteEmployeeFromDb(int id) {
		boolean status=false;
		try {
			String sql="delete from employee where employeeid="+id;
			template.execute(sql);
			status=true;
		}catch (Exception e) {
			status=false;
		}
		return status;
		}
	}


