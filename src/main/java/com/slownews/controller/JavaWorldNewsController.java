package com.slownews.controller;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.slownews.domain.NewsArchive;
import com.slownews.model.*;
import com.slownews.service.JavaWorldNewsImpl;
import com.slownews.service.WeatherForecastImpl;
import org.eclipse.persistence.jaxb.JAXBContextFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.ClientBuilder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
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