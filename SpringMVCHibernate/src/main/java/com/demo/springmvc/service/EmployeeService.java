package com.demo.springmvc.service;

import java.util.List;

import com.demo.springmvc.model.Employee;

public interface EmployeeService {

	List<Employee> AllEmployee();
	void SaveEmployee(Employee employee);
	Boolean Login(Employee employee);
	Employee findById(long empid);
	void UpdateEmployee(Employee emp);
	String deleteUserByID(long empid);
	Employee findByName(String name);
}
