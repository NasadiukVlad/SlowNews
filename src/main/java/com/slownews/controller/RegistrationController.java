package com.slownews.controller;



import com.slownews.model.Registrator;
import com.slownews.model.User;

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
 * Created by Влад on 15.11.2015.
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

        if(userChecker.equals("UserNotExist")) {
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(password);
            users.put(newUser.getUsername(), newUser);
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


/*
String username = request.getParameter("username");
String password = request.getParameter("password");
RequestDispatcher rd = null;

Registrator registrator = new Registrator();

String result = registrator.register(username, password);

if (result.equals(username)) {
        rd = request.getRequestDispatcher("/login.jsp");

        } else {
        rd = request.getRequestDispatcher("/registration.jsp");
        }
        rd.forward(request, response);*/
