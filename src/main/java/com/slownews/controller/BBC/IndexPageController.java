package com.slownews.controller.BBC;


import com.slownews.model.BBC.BBCNews;
import com.slownews.model.Users.User;
import com.slownews.service.BBC.BBCNewsImpl;
import com.slownews.service.WeatherForecast.WeatherForecastImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by Влад on 17.11.2015.
 */
public class IndexPageController extends HttpServlet {


    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        System.out.println(request.toString());

        ServletContext context = request.getSession().getServletContext();
        context.setAttribute("users", users);

        WeatherForecastImpl weatherForecast = new WeatherForecastImpl();
        weatherForecast.getWeatherForecast(request);

        BBCNewsImpl bbcNews = new BBCNewsImpl();
        List<BBCNews> news = new LinkedList<>();
        news = bbcNews.getNews();

        context.setAttribute("news", news);

        Boolean indexFlag = false;
        Boolean archiveFlag = false;

        if ((Boolean) request.getSession().getAttribute("archiveFlag") == null) {
            indexFlag = false;
        } else {
            indexFlag = true;
        }

        context.setAttribute("indexFlag", indexFlag);

        request.getSession().setAttribute("indexFlag", indexFlag);
        request.getSession().setAttribute("archiveFlag", archiveFlag);
        request.getSession().setAttribute("news", news);


        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("WEB-INF/view/startIndex.jsp");
        rd.forward(request, response);

    }

    Map<String, User> users;

    @Override
    public void init() {
        users = new HashMap<>();
        User user = new User();
        user.setUsername("admin");
        user.setPassword("1234");
        users.put(user.getUsername(), user);
    }

}
