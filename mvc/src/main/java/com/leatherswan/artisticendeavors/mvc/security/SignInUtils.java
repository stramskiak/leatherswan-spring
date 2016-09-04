package com.leatherswan.artisticendeavors.mvc.security;

import com.leatherswan.artisticendeavors.mvc.controller.GlobalController;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.ConnectionData;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import com.leatherswan.artisticendeavors.jpa.model.CurrentUser;
import com.leatherswan.artisticendeavors.jpa.model.User;

public class SignInUtils {

	public static void authorizeUser(User user) {

		CurrentUser currentUser = new CurrentUser(user);
		SecurityContextHolder.getContext().setAuthentication(
				new UsernamePasswordAuthenticationToken(currentUser, user.getPassword(), user.getAuthorities()));

	}

	public static void setUserConnection(WebRequest request, ConnectionData connectionData) {
		String attribute = GlobalController.SESSION_USER_CONNECTION;
		request.setAttribute(attribute, connectionData, RequestAttributes.SCOPE_SESSION);
	}

}
