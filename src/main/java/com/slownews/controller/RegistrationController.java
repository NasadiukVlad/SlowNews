package com.slownews.controller;


import com.slownews.dao.UsersJpaDao;
import com.slownews.domain.Users;
import com.slownews.model.Registrator;
import com.slownews.model.User;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.servlet.Registration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by ���� on 15.11.2015.
 */
public class RegistrationController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RegistrationController() {
        super();
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        Map<String, User> users = null;
        ServletContext context = request.getSession().getServletContext();
        Object obj = context.getAttribute("users");

        RequestDispatcher rd = null;

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String userEmail = request.getParameter("userEmail");

        Users user = new Users(username, password, userEmail);
        UsersJpaDao usersJpaDao = new UsersJpaDao();

        usersJpaDao.addUser(user);

        rd = request.getRequestDispatcher("WEB-INF/view/login.jsp");
        rd.forward(request, response);

    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("WEB-INF/view/registration.jsp");
        rd.forward(request, response);
    }
}