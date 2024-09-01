package com.scm.secuitycustom;

import java.io.IOException;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.scm.helper.Message;
import com.scm.helper.MessageType;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AuthfailureHandler implements AuthenticationFailureHandler{

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        if(exception instanceof DisabledException){
            HttpSession session = request.getSession();
            session.setAttribute("message", 
            Message.builder().content("User is Disabled").type(MessageType.red).build());
        }
        response.sendRedirect("/login");
    }

}
