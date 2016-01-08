package com.slownews.service.BBC;

import com.slownews.domain.BBC.NewsArchive;
import com.slownews.model.BBC.BBCXpathMap;
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
public class BBCNewsImpl implements BBCNews {
    @Override
    public List<com.slownews.model.BBC.BBCNews> getNews() throws IOException {
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

        List<com.slownews.model.BBC.BBCNews> news = new LinkedList<>();

        for (int i = 0; i < titles.size(); i++) {

            String title = titles.get(i).toString();
            String description = descriptions.get(i).toString();
            String link = links.get(i).toString();
            com.slownews.model.BBC.BBCNews newsItem = new com.slownews.model.BBC.BBCNews(title, description, link);
            news.add(newsItem);

            NewsArchive newsArchive = new NewsArchive();

            newsArchive.setTitle(title);
            newsArchive.setDescription(description);
            newsArchive.setLink(link);
        }
        xml.close();
        return news;
    }
}
