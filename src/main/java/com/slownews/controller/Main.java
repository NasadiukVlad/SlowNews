package com.slownews.controller;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by Влад on 23.11.2015.
 */
public class Main {
    public static void main(String[] args) throws IOException {

        String responseEntity = ClientBuilder.newClient()
                .target("http://api.openweathermap.org/data/2.5/weather?q=Kiev&appid=2de143494c0b295cca9337e1e96b00e0").path("")
                .request().get(String.class);

        System.out.println(responseEntity);

        JsonFactory factory = new JsonFactory();
        JsonParser parser  = factory.createParser(responseEntity);
        String skyStatus = "";
        String name = "";
        String temp = "";
        String main = "";

        while(!parser.isClosed()) {
            JsonToken jsonToken = parser.nextToken();

            if (JsonToken.FIELD_NAME.equals(jsonToken)) {
                String fieldName = parser.getCurrentName();
                System.out.println(fieldName);


             //   testNextToken = parser.getValueAsString();
                if("name".equals(fieldName)) {
                    jsonToken = parser.nextToken();
                    name = parser.getValueAsString();
                }

                if("description".equals(fieldName)) {
                    jsonToken = parser.nextToken();
                    skyStatus = parser.getValueAsString();
                }

                if("temp".equals(fieldName)) {
                    jsonToken = parser.nextToken();
                    temp = parser.getValueAsString();
                }

                if("main".equals(fieldName)) {
                    jsonToken = parser.nextToken();
                    main = parser.getValueAsString();
                }


             //   System.out.println("jsonToken = " + jsonToken);
            }
        }
        System.out.println("Location: " + name);
        System.out.println("Sky status: " + skyStatus);
        System.out.println("Temperature: " + temp);
        System.out.println("Main: " + main);

    }
}
