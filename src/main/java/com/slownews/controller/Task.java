package com.slownews.controller;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.slownews.model.BBCNews;
import com.slownews.model.BBCXpathMap;
import com.slownews.model.StackOverflowXPathMap;
import org.eclipse.persistence.jaxb.JAXBContextFactory;

import javax.ws.rs.client.ClientBuilder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 * Created by Влад on 25.11.2015.
 */
public class Task {
    public static void main(String[] args) throws JAXBException, IOException {
        String responseEntity = ClientBuilder.newClient()
                .target("http://stackoverflow.com/feeds").path("")
                .request().get(String.class);



        JAXBContext context = JAXBContextFactory.createContext(new Class[]{BBCXpathMap.class}, null);

        Unmarshaller unmarshaller = context.createUnmarshaller();

        URL xmlURL = new URL("http://feeds.bbci.co.uk/news/technology/rss.xml");

        URLConnection conn = xmlURL.openConnection();
        conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.86 Safari/537.36");
        conn.connect();

        InputStream xml = xmlURL.openStream();
        BBCXpathMap bbcXpathMap = (BBCXpathMap) unmarshaller.unmarshal(xml);

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
        }

        Iterator<BBCNews> newsIterator = news.iterator();

        while (newsIterator.hasNext()) {
            System.out.println(newsIterator.next().getTitle());
            System.out.println(newsIterator.next().getDescription());
            System.out.println(newsIterator.next().getLink());
            System.out.println("------------------------------------------------------");
        }

       /* titles = bbcXpathMap.getTitles();
        links = bbcXpathMap.getLinks();
        descriptions = bbcXpathMap.getDescriptions();

        System.out.println(titles.get(0));
        System.out.println(descriptions.get(0));
        System.out.println(links.get(0));
        System.out.println(descriptions.get(0));*/




        xml.close();

       /* Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(bbcXpathMap, System.out);*/


    }
}
