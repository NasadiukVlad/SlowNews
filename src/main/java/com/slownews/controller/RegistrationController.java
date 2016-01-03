package com.slownews.controller;


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

        if (obj instanceof Map) {
            users = (Map) obj;
        }

        RequestDispatcher rd = null;

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Registrator registrator = new Registrator();

        String userChecker = registrator.register(users, username, password);

        if (userChecker.equals("UserNotExist")) {
            Users user = new Users();

            user.setUsername(username);
            user.setPassword(password);

            EntityManager entityManager = Persistence.createEntityManagerFactory("tutorialPU").createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            entityManager.close();
            rd = request.getRequestDispatcher("WEB-INF/view/login.jsp");

        } else {
            rd = request.getRequestDispatcher("WEB-INF/view/registration.jsp");
        }

        rd.forward(request, response);

    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("WEB-INF/view/registration.jsp");
        rd.forward(request, response);
    }
}