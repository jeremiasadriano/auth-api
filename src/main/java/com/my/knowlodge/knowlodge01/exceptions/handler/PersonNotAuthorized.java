package com.my.knowlodge.knowlodge01.exceptions.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class PersonNotAuthorized implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String message = "UNAUTHORIZED";
        int status = 401;
        response.getWriter().write("status: " + status + "\n" + "message: " + message);
        response.setStatus(status);
    }
}
