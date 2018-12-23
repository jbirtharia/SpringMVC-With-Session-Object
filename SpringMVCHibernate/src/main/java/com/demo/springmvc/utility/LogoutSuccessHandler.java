package com.demo.springmvc.utility;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
			org.springframework.security.core.Authentication authentication) throws IOException, ServletException 
	{
		response.setHeader("Cache-Control","no-cache"); //Forces caches to obtain a new copy of the page from the origin server
		response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
		response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"  
		response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null) //In session    	 
	    	super.onLogoutSuccess(request, response, authentication);
	   
	    else //Not in Session
	    	super.onLogoutSuccess(request, response, authentication);
	   
	}
	
}
