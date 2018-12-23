package com.demo.springmvc.security;


import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.demo.springmvc.model.Employee;
import com.demo.springmvc.service.EmployeeService;
import com.demo.springmvc.utility.UserContext;



@Service
public class CustomUserDetailsService implements UserDetailsService{

	private static final Logger LOGGER = Logger.getLogger(CustomUserDetailsService.class.getName());


	@Autowired
	EmployeeService employeeService;
	
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String name)
			throws UsernameNotFoundException {
		Employee employee = employeeService.findByName(name);
		if(employee==null){
			throw new UsernameNotFoundException("Username not found");
		}
		
		UserContext userContext=new UserContext(employee.getUsername(),employee.getPass(), true, true, true, true, getGrantedAuthorities(employee));
		userContext.setEmpid(employee.getEmpid());
		userContext.setName(employee.getName());
		userContext.setPass(employee.getPass());
		userContext.setSal(employee.getSal());
		userContext.setRole(employee.getRole());
		userContext.setUsername(employee.getUsername());
		
		LOGGER.info("UserContext {} :"+userContext.toString());
		return userContext;
	}

	
	private List<GrantedAuthority> getGrantedAuthorities(Employee employee){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_"+employee.getRole()));
		return authorities;
	}
	

}
