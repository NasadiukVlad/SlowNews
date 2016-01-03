package com.slownews.controller;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.slownews.domain.NewsArchive;
import com.slownews.model.*;
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
            contextJAXB = JAXBContextFactory.createContext(new Class[]{HabrahabrXpathMap.class}, null);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        Unmarshaller unmarshaller = null;
        try {
            unmarshaller = contextJAXB.createUnmarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        URL xmlURL = new URL("http://habrahabr.ru/rss/hubs/all/");

        URLConnection conn = xmlURL.openConnection();
        conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.86 Safari/537.36");
        conn.connect();

        InputStream xml = xmlURL.openStream();
        HabrahabrXpathMap habrahabrXpathMap = null;

        try {
            habrahabrXpathMap = (HabrahabrXpathMap) unmarshaller.unmarshal(xml);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        List titles = new ArrayList<String>();
        List links = new ArrayList<String>();
        List descriptions = new ArrayList<String>();

        titles = habrahabrXpathMap.getTitles();
        links = habrahabrXpathMap.getLinks();
        descriptions = habrahabrXpathMap.getDescriptions();

        List<HabrahabrNews> news = new LinkedList<>();

        for (int i = 0; i < titles.size(); i++) {
            String title = titles.get(i).toString();
            String description = descriptions.get(i).toString().replaceAll("\\<.*?>", "");
            int decriptionLength = description.length();

            StringBuilder sb = new StringBuilder();

            if (decriptionLength > 450) {
                sb.append(description.substring(0, 450));
                sb.append("...");
            } else {
                sb.append(description.substring(0, decriptionLength - 21));
            }

            String link = links.get(i).toString();

            HabrahabrNews newsItem = new HabrahabrNews(title, sb.toString(), link);
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

        request.getSession().setAttribute("news", news);

        xml.close();

        RequestDispatcher rd = null;
        rd = request.getRequestDispatcher("WEB-INF/view/HabrahabrMainNews.jsp");
        rd.forward(request, response);

    }
}
