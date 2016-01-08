package com.slownews.controller.JavaWorld;

import com.slownews.model.JavaWorld.JavaWorldNews;
import com.slownews.model.Users.User;
import com.slownews.service.JavaWorld.JavaWorldNewsImpl;
import com.slownews.service.WeatherForecast.WeatherForecastImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Влад on 02.01.2016.
 */
public class JavaWorldNewsController extends HttpServlet {
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        System.out.println(request.toString());

        Map<String, User> users = null;
        ServletContext context = request.getSession().getServletContext();
        Object obj = context.getAttribute("users");

        WeatherForecastImpl weatherForecast = new WeatherForecastImpl();
        weatherForecast.getWeatherForecast(request);

        JavaWorldNewsImpl javaWorldNews = new JavaWorldNewsImpl();
        List<JavaWorldNews> javaNews = new LinkedList<>();
        javaNews = javaWorldNews.getJavaWorldNews();

        context.setAttribute("javaNews", javaNews);

        Boolean indexFlag = false;
        Boolean archiveFlag = false;

        if ((Boolean) request.getSession().getAttribute("javaWorldArchiveFlag") == null) {
            indexFlag = false;
        } else {
            indexFlag = true;
        }

        context.setAttribute("indexFlag", indexFlag);

        request.getSession().setAttribute("javaWorldIndexFlag", indexFlag);
        request.getSession().setAttribute("javaWorldArchiveFlag", archiveFlag);
        request.getSession().setAttribute("javaWorldNews", javaWorldNews);

        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("WEB-INF/view/JavaWorldNews.jsp");
        response.setCharacterEncoding("windows-1251");
        rd.forward(request, response);
    }
}