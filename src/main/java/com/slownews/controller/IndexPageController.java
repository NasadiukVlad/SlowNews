package com.slownews.controller;



import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.slownews.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.ClientBuilder;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Влад on 17.11.2015.
 */
public class IndexPageController extends HttpServlet {


    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        System.out.println(request.toString());

        ServletContext context = request.getSession().getServletContext();
        context.setAttribute("users", users);


        String responseEntity = ClientBuilder.newClient()
                .target("http://api.openweathermap.org/data/2.5/weather?q=Kiev&appid=2de143494c0b295cca9337e1e96b00e0").path("")
                .request().get(String.class);

        System.out.println(responseEntity);

        JsonFactory factory = new JsonFactory();
        JsonParser parser  = factory.createParser(responseEntity);
        String skyStatus = "";
        String name = "";
        String temp = "";
        String windSpeed = "";

        while(!parser.isClosed()) {
            JsonToken jsonToken = parser.nextToken();

            if (JsonToken.FIELD_NAME.equals(jsonToken)) {
                String fieldName = parser.getCurrentName();
               // System.out.println(fieldName);


                //   testNextToken = parser.getValueAsString();
                if("name".equals(fieldName)) {
                    jsonToken = parser.nextToken();
                    name = parser.getValueAsString();
                    request.getSession().setAttribute("location", name);
                }

                if("description".equals(fieldName)) {
                    jsonToken = parser.nextToken();
                    skyStatus = parser.getValueAsString();
                    request.getSession().setAttribute("skyStatus", skyStatus);
                }

                if("temp".equals(fieldName)) {
                    jsonToken = parser.nextToken();
                    temp = parser.getValueAsString();
                    request.getSession().setAttribute("temp", temp);
                }

                if("speed".equals(fieldName)) {
                    jsonToken = parser.nextToken();
                    windSpeed = parser.getValueAsString();
                    request.getSession().setAttribute("windSpeed", windSpeed);
                }




                //   System.out.println("jsonToken = " + jsonToken);
            }
        }

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
