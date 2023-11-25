package com.rio.messenger.filter;

import com.rio.messenger.util.AuthUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;


public class SessionFilter implements Filter {

    AuthUtil authUtil;

    public SessionFilter(AuthUtil authUtil){
        this.authUtil = authUtil;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (request.getMethod().equals("POST")){
            JSONObject body = new JSONObject(request.getReader().lines().collect(Collectors.joining()));
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            if (body.get("username") == null ){
                response.setStatus(400);
                response.getOutputStream().write("Invalid input".getBytes(StandardCharsets.UTF_8));
                return;
            }
            String username = body.getString("username");
            if (authUtil.isSessionActive(username)){
                filterChain.doFilter(servletRequest,servletResponse);
            } else {
                response.setStatus(440);
                response.getOutputStream().write("Session expired".getBytes(StandardCharsets.UTF_8));
                return;
            }

        }
    }
}
