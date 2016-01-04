package com.slownews.controller;



import com.slownews.dao.UsersJpaDao;
import com.slownews.domain.Users;
import com.slownews.model.Authenticator;
import com.slownews.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Влад on 14.11.2015.
 */


public class LoginController extends HttpServlet {

       public LoginController() {
        super();
    }



    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        Map<String, User> users = null;
        ServletContext context = request.getSession().getServletContext();

        RequestDispatcher rd = null;

        String username = request.getParameter("username");
        String password = request.getParameter("password");

     /*   Authenticator authenticator = new Authenticator();
        String result = authenticator.authenticate(users, username, password);
*/

        UsersJpaDao usersJpaDao = new UsersJpaDao();
        Users user = usersJpaDao.getByLogin(username);

        if(user != null) {
            if (user.getUsername().equals(username) &&
                    user.getPassword().equals(password)) {
                context.setAttribute("username", username);
                rd = request.getRequestDispatcher("WEB-INF/view/startIndex.jsp");
            } else if (!user.getUsername().equals(username) ||
                    !user.getPassword().equals(password)) {
                context.setAttribute("error", "Login and password not match!");
                rd = request.getRequestDispatcher("WEB-INF/view/registration.jsp");
            }

        }

       /* if (result.equals("userCanLogin")) {
         //   context.setAttribute("userLoged", username);
            request.getSession().setAttribute("username", username);
            rd = request.getRequestDispatcher("WEB-INF/view/login.jsp");

        } else {
            rd = request.getRequestDispatcher("WEB-INF/view/login.jsp");
        }*/
        rd.forward(request, response);
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {


        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("WEB-INF/view/login.jsp");
        rd.forward(request, response);
    }

}