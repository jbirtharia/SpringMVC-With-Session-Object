package com.demo.springmvc.dao;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.demo.springmvc.model.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Employee> GetAllEmployee() {
		return sessionFactory.getCurrentSession().createCriteria(Employee.class).list();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Integer> CheckLogin(Employee employee) {
		Query q=sessionFactory.getCurrentSession().createQuery("from Employee where emp_email=:u and emp_pass=:p");
		q.setParameter("u", employee.getUsername());
		q.setParameter("p", employee.getPass());
		return q.list();
	}
	
	@Transactional
	public void Save(Employee employee) {
		sessionFactory.getCurrentSession().save(employee);
		
	}

	@Transactional
	public Employee FindEmpId(long empid) {
		return (Employee) sessionFactory.getCurrentSession().get(Employee.class,empid) ;
	}

	@Transactional
	public void Update(Employee employee) {
		sessionFactory.getCurrentSession().update(employee);
		
	}

	@Transactional
	public String Delete(long empid) {
		Employee employee=(Employee) sessionFactory.getCurrentSession().get(Employee.class,empid) ;
		sessionFactory.getCurrentSession().delete(employee);
		return employee.getName();
		
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Employee FindEmpName(String name) {
		Query q=sessionFactory.getCurrentSession().createQuery("from Employee where emp_email=:u");
		q.setParameter("u", name);
		ArrayList<Employee> employees=(ArrayList<Employee>) q.list();
		return employees.get(0);
	}
	
	
}
