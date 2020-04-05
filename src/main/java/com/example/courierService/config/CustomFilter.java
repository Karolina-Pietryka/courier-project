package com.example.courierService.config;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class CustomFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String session = (String) req.getSession().getAttribute("login");
        String requestUri = req.getRequestURI();


        if (
                session != null ||
                        requestUri.endsWith(".css") ||
                        requestUri.endsWith(".jpg") ||
                        requestUri.endsWith(".png") ||
                        requestUri.endsWith("login")

        ) {
            filterChain.doFilter(req, resp);
        } else {
            resp.sendRedirect(resp.encodeRedirectURL("login"));
        }
    }

    @Override
    public void destroy() {

    }
}
