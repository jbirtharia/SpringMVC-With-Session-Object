package com.demo.springmvc.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.demo.springmvc.model.Employee;
import com.demo.springmvc.service.EmployeeService;
import com.demo.springmvc.utility.UserContextFactory;


@Controller
public class AppController {

	private static final Logger LOGGER = Logger.getLogger(AppController.class.getName());

	@Autowired
	EmployeeService employeeService;

	@RequestMapping(value = {"/list" }, method = RequestMethod.GET)
	public String listUsers(Model model) {
		if(UserContextFactory.getUserContext().getUsername()!=null)
		{
			model.addAttribute("empObj",employeeService.AllEmployee());
			model.addAttribute("loggedinuser", UserContextFactory.getUserContext().getName());
			return "userslist";
		}
		else
			return "redirect:/loginpage";

	}

	@RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		model.addAttribute("employee", new Employee());
		model.addAttribute("loggedinuser", UserContextFactory.getUserContext().getName());
		model.addAttribute("edit", false);
		return "registration";
	}

	@RequestMapping(value = { "/newuser" }, method = RequestMethod.POST)
	public String saveUser(@Valid Employee employee, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}
		employeeService.SaveEmployee(employee); 
		model.addAttribute("success", "User " + employee.getName() + " registered successfully");
		return "registrationsuccess";
	}


	@RequestMapping(value = {"/" ,"/loginpage" }, method = RequestMethod.GET)
	public String newUser(Model model) {
		if(!model.containsAttribute("employeeBean"))
			model.addAttribute("employeeBean",new Employee());

		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(@Valid @ModelAttribute("employeeBean") Employee employee,final RedirectAttributes redirectAttributes) {

		if (employeeService.Login(employee))
		{	redirectAttributes.addFlashAttribute("message","Invalid username and password.");
			return "redirect:/loginerror";
		}
		else 
			return "redirect:/list";  
	}
	

	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response){
		UserContextFactory.getUserContext().setUsername(null);
		UserContextFactory.getUserContext().setName(null);
		LOGGER.info("UserContext {} : "+UserContextFactory.getUserContext());
		return "redirect:/loginpage?logout";
	}


	@RequestMapping(value = { "/edit-user-{empid}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable long empid, ModelMap model) {
		Employee employee = employeeService.findById(empid);
		LOGGER.info("Employee : "+employee);
		model.addAttribute("emp", employee);
		model.addAttribute("edit", true);
		return "registration";
	}


	@RequestMapping(value = { "/edit-user-{empid}" }, method = RequestMethod.POST)
	public String updateUser(@Valid Employee emp, BindingResult result,
			ModelMap model, @PathVariable long empid) {

		if (result.hasErrors()) {
			return "registration";
		}
		employeeService.UpdateEmployee(emp);
		model.addAttribute("success", "User " + emp.getName() + " updated successfully");

		return "registrationsuccess";
	}

	@RequestMapping(value = { "/delete-user-{empid}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable long empid,ModelMap model) {
		
		model.addAttribute("success", "User " + employeeService.deleteUserByID(empid) + " delete successfully");
		return "registrationsuccess";
	}

	@RequestMapping(value = { "/listtool" }, method = RequestMethod.GET)
	public ModelAndView ListTool() {
		ModelAndView mav=new ModelAndView("toolTip");
		List<Employee> list=employeeService.AllEmployee();
		LOGGER.info("List of Employees : "+list);
		mav.addObject("empObj",list);
		return mav;
	}

}