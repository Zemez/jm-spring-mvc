package com.javamentor.jm_spring_mvc.handler;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exp)
            throws IOException, ServletException {
        if (exp.getClass().isAssignableFrom(BadCredentialsException.class)
                || exp.getClass().isAssignableFrom(InternalAuthenticationServiceException.class)) {
            request.getSession().setAttribute("error", "Invalid username or password.");
        } else {
            request.getSession().setAttribute("error", exp.getMessage());
        }
        response.sendRedirect("/signin");
    }

}
