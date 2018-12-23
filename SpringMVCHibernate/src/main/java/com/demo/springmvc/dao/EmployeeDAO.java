package com.demo.springmvc.dao;

import java.util.List;

import com.demo.springmvc.model.Employee;

public interface EmployeeDAO {
	
	List<Employee> GetAllEmployee();
	List<Integer> CheckLogin(Employee employee);
	void Save(Employee employee);
	Employee FindEmpId(long empid);
	void Update(Employee employee);
	String Delete(long empid);
	Employee FindEmpName(String name);

}
