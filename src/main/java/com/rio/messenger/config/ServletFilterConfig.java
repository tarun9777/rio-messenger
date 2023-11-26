package com.rio.messenger.config;

import com.rio.messenger.filter.SessionFilter;
import com.rio.messenger.util.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ServletFilterConfig {


    /**
     * This bean creation will add the servlet filter to
     * validate the active session for the logged-in users.
     *
     * @param authUtil
     * @return FilterRegistrationBean
     */
    @Bean
    @Autowired
    public FilterRegistrationBean<SessionFilter> sessionFilter(AuthUtil authUtil){
        FilterRegistrationBean<SessionFilter> frb = new FilterRegistrationBean<>();
        frb.setFilter(new SessionFilter(authUtil));
        frb.addUrlPatterns("/get/unread","/send/text/user","/logout");
        return frb;
    }
}
