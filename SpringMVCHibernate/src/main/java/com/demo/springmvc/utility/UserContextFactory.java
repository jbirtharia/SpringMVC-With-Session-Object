 package com.demo.springmvc.utility;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserContextFactory {

	public static UserContext getUserContext()
	{
		if(SecurityContextHolder.getContext().getAuthentication()!=null)
				return (UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		else 
			return null;
	}

	public static void  setUserContext(UserContext userContext)
	{
		Authentication authentication = new UsernamePasswordAuthenticationToken(userContext, userContext.getPassword(),userContext.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
	
}
