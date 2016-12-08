package com.stefletcher;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by 2ball on 12/6/2016.
 */
public class DummyServletFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("I AM FILTER!");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
