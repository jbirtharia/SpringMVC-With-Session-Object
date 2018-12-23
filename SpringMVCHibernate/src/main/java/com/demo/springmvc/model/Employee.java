package com.demo.springmvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="Employee")
public class Employee {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long empid;
	
	@NotEmpty(message="Name cannot be empty.")
	@Column(name="emp_name")
	private String name;
	
	@NotNull(message="Salary cannot be empty.")
	@Column(name="emp_salary")
	private Double sal;
	
	@NotEmpty(message="UserName cannot be empty.")
	@Column(name="emp_email")
	private String username;
	
	@NotEmpty(message="Password cannot be empty.")
	@Column(name="emp_pass")
	private String pass;
	
	@Column(name="ROLE")
	private String role = UserProfileType.USER.getUserProfileType();

	

	public long getEmpid() {
		return empid;
	}

	public void setEmpid(long empid) {
		this.empid = empid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getSal() {
		return sal;
	}

	public void setSal(Double sal) {
		this.sal = sal;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Employee [empid=" + empid + ", name=" + name + ", sal=" + sal + ", username=" + username + ", pass="
				+ pass + "]";
	}
	
	
	
	
}
