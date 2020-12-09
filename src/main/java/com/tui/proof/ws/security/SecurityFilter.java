package com.tui.proof.ws.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SecurityFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(SecurityFilter.class);


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        try {
            res.setHeader("Access-Control-Allow-Credentials", "true");
            res.setHeader("Access-Control-Max-Age", "3600");
            res.setHeader("Access-Control-Allow-Origin", "*");
            res.setHeader("Access-Control-Allow-Methods", "GET,HEAD,OPTIONS,POST,PUT");
            res.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");

            String url = req.getRequestURI();
            // any Business Logic we can add here to bypass
            if(! (url.contains("swagger") || url.contains("api-docs")||  url.contains("actuator") || url.contains("/ping")))
            {

                // any Business Logic we can add here to check the security
                if (url.contains("/admin"))
                {
                // any Business Logic we can add here to check the security
                }
            }
        } catch (Exception ex) {
            logger.warn("Could not set user authentication in security context");
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getLocalizedMessage());
            return ;
        }
        filterChain.doFilter(request, response);
    }
}
