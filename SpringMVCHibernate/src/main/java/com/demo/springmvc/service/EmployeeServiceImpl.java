package com.demo.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.springmvc.dao.EmployeeDAO;
import com.demo.springmvc.model.Employee;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	EmployeeDAO employeeDAO;

	public List<Employee> AllEmployee() {
		return employeeDAO.GetAllEmployee();
	}


	public Boolean Login(Employee employee) {
		if (employeeDAO.CheckLogin(employee).size()==0) 
			return true;
		else
			return false;
	}
	
	
	public void SaveEmployee(Employee employee) {
		employeeDAO.Save(employee);
		
	}

	public Employee findById(long empid) {
		return employeeDAO.FindEmpId(empid);
	}

	public void UpdateEmployee(Employee employee) {
		employeeDAO.Update(employee);
		
	}

	public String deleteUserByID(long empid) {
		return employeeDAO.Delete(empid);
		
	}

	public Employee findByName(String name) {
		return employeeDAO.FindEmpName(name);
	}


}
