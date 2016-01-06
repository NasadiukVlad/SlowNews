package com.slownews.controller;


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.slownews.domain.NewsArchive;
import com.slownews.model.BBCNews;
import com.slownews.model.BBCXpathMap;
import com.slownews.model.User;
import org.eclipse.persistence.jaxb.JAXBContextFactory;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
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


        String responseEntity = ClientBuilder.newClient()
                .target("http://api.openweathermap.org/data/2.5/weather?q=Kiev&appid=2de143494c0b295cca9337e1e96b00e0").path("")
                .request().get(String.class);

        System.out.println(responseEntity);

        JsonFactory factory = new JsonFactory();
        JsonParser parser = factory.createParser(responseEntity);
        String skyStatus = "";
        String name = "";
        String temp = "";
        String windSpeed = "";

        while (!parser.isClosed()) {
            JsonToken jsonToken = parser.nextToken();

            if (JsonToken.FIELD_NAME.equals(jsonToken)) {
                String fieldName = parser.getCurrentName();
                // System.out.println(fieldName);


                //   testNextToken = parser.getValueAsString();
                if ("name".equals(fieldName)) {
                    jsonToken = parser.nextToken();
                    name = parser.getValueAsString();
                    request.getSession().setAttribute("location", name);
                }

                if ("description".equals(fieldName)) {
                    jsonToken = parser.nextToken();
                    skyStatus = parser.getValueAsString();
                    request.getSession().setAttribute("skyStatus", skyStatus);
                }

                if ("temp".equals(fieldName)) {
                    jsonToken = parser.nextToken();
                    temp = parser.getValueAsString();
                    request.getSession().setAttribute("temp", temp);
                }

                if ("speed".equals(fieldName)) {
                    jsonToken = parser.nextToken();
                    windSpeed = parser.getValueAsString();
                    request.getSession().setAttribute("windSpeed", windSpeed);
                }

                //   System.out.println("jsonToken = " + jsonToken);
            }
        }

        JAXBContext contextJAXB = null;
        try {
            contextJAXB = JAXBContextFactory.createContext(new Class[]{BBCXpathMap.class}, null);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        Unmarshaller unmarshaller = null;
        try {
            unmarshaller = contextJAXB.createUnmarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        URL xmlURL = new URL("http://feeds.bbci.co.uk/news/technology/rss.xml");

        URLConnection conn = xmlURL.openConnection();
        conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.86 Safari/537.36");
        conn.connect();

        InputStream xml = xmlURL.openStream();
        BBCXpathMap bbcXpathMap = null;

        try {
            bbcXpathMap = (BBCXpathMap) unmarshaller.unmarshal(xml);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        List titles = new ArrayList<String>();
        List links = new ArrayList<String>();
        List descriptions = new ArrayList<String>();

        titles = bbcXpathMap.getTitles();
        links = bbcXpathMap.getLinks();
        descriptions = bbcXpathMap.getDescriptions();

        List<BBCNews> news = new LinkedList<>();


        for (int i = 0; i < titles.size(); i++) {

            String title = titles.get(i).toString();
            String description = descriptions.get(i).toString();
            String link = links.get(i).toString();
            BBCNews newsItem = new BBCNews(title, description, link);
            news.add(newsItem);

            NewsArchive newsArchive = new NewsArchive();

            newsArchive.setTitle(title);
            newsArchive.setDescription(description);
            newsArchive.setLink(link);

          /*  EntityManager entityManager = Persistence.createEntityManagerFactory("tutorialPU").createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(newsArchive);
            entityManager.getTransaction().commit();
            entityManager.close();*/

        }
        context.setAttribute("news", news);

        Boolean indexFlag = false;
        Boolean archiveFlag = false;

        if((Boolean)request.getSession().getAttribute("archiveFlag") == null) {
            indexFlag = false;
        }else if((boolean)request.getSession().getAttribute("archiveFlag") == true) {
            indexFlag = true;
        }


        request.getSession().setAttribute("indexFlag", indexFlag);
        request.getSession().setAttribute("archiveFlag", archiveFlag);
        request.getSession().setAttribute("news", news);
               xml.close();

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
