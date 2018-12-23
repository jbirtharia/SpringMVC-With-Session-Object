package com.demo.springmvc.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.demo.springmvc.utility.UserContextFactory;

@ControllerAdvice
public class AdviceController {


@ExceptionHandler(NoHandlerFoundException.class)
public ModelAndView exception(Exception e) {
	
	ModelAndView mav = new ModelAndView("accessDenied");
	try {
	mav.addObject("loggedinuser", UserContextFactory.getUserContext().getName());
	}
	catch (Exception e1) {
		mav.addObject("loggedinuser", "User");
	}
	
	return mav;
}


}
