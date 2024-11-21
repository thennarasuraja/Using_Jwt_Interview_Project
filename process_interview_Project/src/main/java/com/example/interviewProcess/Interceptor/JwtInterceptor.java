package com.example.interviewProcess.Interceptor;

import com.example.interviewProcess.Utill.JwtUtill;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtill jwtUtill;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("path"+request.getRequestURI());

        // Allow preflight (OPTIONS) requests to pass through without validation
        if (request.getMethod().equalsIgnoreCase("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        System.out.println("This is pre Handle");
        String auth=request.getHeader("Authorization");
        System.out.println("Authorization Header: " +auth);

        if(!(request.getRequestURI().contains("register") || request.getRequestURI().contains("login"))){
            System.out.println("cdgnjvf,fdv f,gbdfgb fb fdgb,d bfnot inside register,login");

            if (auth == null || auth.isEmpty()) {
                throw new RuntimeException("Missing authorization header");
            }
            jwtUtill.validateToken(auth);
        }

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
