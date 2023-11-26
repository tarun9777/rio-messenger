package com.rio.messenger.filter;

import com.rio.messenger.util.AuthUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;

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
        ContentCachingRequestWrapper request = new ContentCachingRequestWrapper((HttpServletRequest) servletRequest);
        if (request.getMethod().equals("POST")){
            String token = request.getHeader("x-token");
            String username = request.getHeader("x-username");
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            if (token == null || token.isEmpty()){
                response.setStatus(401);
                response.getOutputStream().write("auth token missing in header".getBytes(StandardCharsets.UTF_8));
                return;
            }
            if (username == null || username.isEmpty()){
                response.setStatus(401);
                response.getOutputStream().write("username missing in header".getBytes(StandardCharsets.UTF_8));
                return;
            }
            if (authUtil.isSessionActive(username,token)){
                filterChain.doFilter(servletRequest,servletResponse);
            } else {
                response.setStatus(440);
                response.getOutputStream().write("No session found please login".getBytes(StandardCharsets.UTF_8));
                return;
            }

        }
    }
}
