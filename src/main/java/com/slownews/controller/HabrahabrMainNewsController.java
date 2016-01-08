package com.slownews.controller;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.slownews.domain.NewsArchive;
import com.slownews.model.*;
import com.slownews.service.HabrahabrNewsImpl;
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
public class HabrahabrMainNewsController extends HttpServlet {
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        System.out.println(request.toString());

        Map<String, User> users = null;
        ServletContext context = request.getSession().getServletContext();
        Object obj = context.getAttribute("users");

        WeatherForecastImpl weatherForecast = new WeatherForecastImpl();
        weatherForecast.getWeatherForecast(request);

        HabrahabrNewsImpl habrahabrNews = new HabrahabrNewsImpl();

        List<HabrahabrNews> habraNews = new LinkedList<>();
        habraNews = habrahabrNews.getHabrahabrNews();

        context.setAttribute("habraNews", habraNews);

        Boolean indexFlag = false;
        Boolean archiveFlag = false;

        if((Boolean)request.getSession().getAttribute("habrahabrArchiveFlag") == null) {
            indexFlag = false;
        } else {
            indexFlag = true;
        }

        context.setAttribute("indexFlag", indexFlag);

        request.getSession().setAttribute("habrahabrIndexFlag", indexFlag);
        request.getSession().setAttribute("habrahabrArchiveFlag", archiveFlag);
        request.getSession().setAttribute("habrahabrNews", habrahabrNews);

        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("WEB-INF/view/HabrahabrMainNews.jsp");
        rd.forward(request, response);

    }
}
