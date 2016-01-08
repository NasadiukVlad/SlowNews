package com.slownews.service.Habrahabr;

import com.slownews.domain.BBC.NewsArchive;
import com.slownews.model.Habrahabr.HabrahabrXpathMap;
import org.eclipse.persistence.jaxb.JAXBContextFactory;

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

/**
 * Created by Влад on 08.01.2016.
 */
public class HabrahabrNewsImpl implements HabrahabrNews {

    @Override
    public List<com.slownews.model.Habrahabr.HabrahabrNews> getHabrahabrNews() throws IOException {
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

        List<com.slownews.model.Habrahabr.HabrahabrNews> habrahabrNews = new LinkedList<>();

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

            com.slownews.model.Habrahabr.HabrahabrNews newsItem = new com.slownews.model.Habrahabr.HabrahabrNews(title, sb.toString(), link);
            habrahabrNews.add(newsItem);

            NewsArchive newsArchive = new NewsArchive();

            newsArchive.setTitle(title);
            newsArchive.setDescription(description);
            newsArchive.setLink(link);

        }

        xml.close();
        return habrahabrNews;
    }
}
