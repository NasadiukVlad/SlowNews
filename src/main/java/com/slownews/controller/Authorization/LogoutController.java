package com.slownews.controller.Authorization;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Влад on 02.12.2015.
 */
public class LogoutController extends HttpServlet {
    public LogoutController() {
        super();
    }

    protected  void doGet(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("username", "");

        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("WEB-INF/view/login.jsp");
        rd.forward(request, response);

    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
    }
}
