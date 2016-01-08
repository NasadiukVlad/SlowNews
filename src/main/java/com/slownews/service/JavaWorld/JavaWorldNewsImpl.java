package com.slownews.service.JavaWorld;

import com.slownews.domain.BBC.NewsArchive;
import com.slownews.model.JavaWorld.JavaWorldNewsXPathMap;
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
public class JavaWorldNewsImpl implements JavaWorldNews {

    @Override
    public List<com.slownews.model.JavaWorld.JavaWorldNews> getJavaWorldNews() throws IOException {
        JAXBContext contextJAXB = null;
        try {
            contextJAXB = JAXBContextFactory.createContext(new Class[]{JavaWorldNewsXPathMap.class}, null);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        Unmarshaller unmarshaller = null;
        try {
            unmarshaller = contextJAXB.createUnmarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        URL xmlURL = new URL("http://www.javaworld.com/index.rss");

        URLConnection conn = xmlURL.openConnection();
        conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.86 Safari/537.36");
        conn.connect();

        InputStream xml = xmlURL.openStream();
        JavaWorldNewsXPathMap javaWorldNewsXPathMap = null;

        try {
            javaWorldNewsXPathMap = (JavaWorldNewsXPathMap) unmarshaller.unmarshal(xml);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        List titles = new ArrayList<String>();
        List links = new ArrayList<String>();
        List descriptions = new ArrayList<String>();

        titles = javaWorldNewsXPathMap.getTitles();
        links = javaWorldNewsXPathMap.getLinks();
        descriptions = javaWorldNewsXPathMap.getDescriptions();

        List<com.slownews.model.JavaWorld.JavaWorldNews> javaWorldNews = new LinkedList<>();


        for (int i = 0; i < titles.size(); i++) {

            String title = titles.get(i).toString();
            String description = descriptions.get(i).toString().replaceAll("\\<.*?>", "");
            String link = links.get(i).toString();

            int decriptionLength = description.length();

            StringBuilder sb = new StringBuilder();

            if (decriptionLength > 450) {
                sb.append(description.substring(0, 450));
                sb.append("...");
            } else {
                sb.append(description.substring(0, decriptionLength - 21));
            }

            com.slownews.model.JavaWorld.JavaWorldNews newsItem = new com.slownews.model.JavaWorld.JavaWorldNews(title, sb.toString(), link);
            javaWorldNews.add(newsItem);

            NewsArchive newsArchive = new NewsArchive();

            newsArchive.setTitle(title);
            newsArchive.setDescription(description);
            newsArchive.setLink(link);

        }
        xml.close();

        return javaWorldNews;
    }
}
