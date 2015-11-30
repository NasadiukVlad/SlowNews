package com.slownews.controller;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.slownews.model.StackOverflowXPathMap;

import javax.ws.rs.client.ClientBuilder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Влад on 25.11.2015.
 */
public class Task {
    public static void main(String[] args) throws JAXBException, IOException {
        String responseEntity = ClientBuilder.newClient()
                .target("http://stackoverflow.com/feeds").path("")
                .request().get(String.class);


      //  System.out.println(responseEntity);


        /*JAXBContext context = JAXBContext.newInstance(StackOverflowXPathMap.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        StackOverflowXPathMap stackOverflowXPathMap = (StackOverflowXPathMap) unmarshaller.unmarshal(new StringReader("<?xml..."));*/

        JAXBContext jc = JAXBContext.newInstance(StackOverflowXPathMap.class);

        Unmarshaller unmarshaller = jc.createUnmarshaller();

        URL xmlURL = new URL("http://stackoverflow.com/feeds");

        URLConnection conn = xmlURL.openConnection();
        conn.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.86 Safari/537.36");
        conn.connect();

        InputStream xml = xmlURL.openStream();
        StackOverflowXPathMap stackOverflowXPathMap = (StackOverflowXPathMap) unmarshaller.unmarshal(xml);
        System.out.println(stackOverflowXPathMap.getAuthor());
        xml.close();

        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(stackOverflowXPathMap, System.out);


    }
}
