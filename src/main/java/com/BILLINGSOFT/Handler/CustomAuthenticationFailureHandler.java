package com.BILLINGSOFT.Handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.BILLINGSOFT.Entity.User;
import com.BILLINGSOFT.Repository.UserRepository;
import com.BILLINGSOFT.Service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
    
    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
    	
        String username = request.getParameter("username");
        User user = userRepository.findUserByUsername(username);

        if (user != null && !user.isIslocked()) {
            userService.increaseFailedAttempts(user);
        }
        
        String errorMessage = null;
        
        Throwable cause = exception;
        while (cause != null) {
            if (cause instanceof LockedException) {
                errorMessage = cause.getMessage();
                break;
            }
            cause = cause.getCause();
        }

        if (cause == null) {
            errorMessage = "Invalid username or password";
        }

        errorMessage = URLEncoder.encode(errorMessage, StandardCharsets.UTF_8.toString());
        response.sendRedirect(contextPath + "/login?error=" + errorMessage);
    }
}

