package com.stefletcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 2ball on 12/5/2016.
 */
@EnableAutoConfiguration
public class DummyServlet extends HttpServlet {

    public DummyServlet(CMDBRepository cMDBRepository) {
        this.cMDBRepository = cMDBRepository;
    }


    public DummyServlet() {
    }

    @Autowired
    private CMDBRepository cMDBRepository;

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

        System.out.println("IN HERE>>>");
        ServiceDefinition sd = cMDBRepository.findByLineOfBusiness("branch").get(0);
        res.getWriter().print("Hello Spring Servlet!" +sd);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("I AM SERVLET!");
        super.doGet(req, resp);
    }
}
