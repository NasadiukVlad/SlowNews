package com.slownews.service.WeatherForecast;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.slownews.service.WeatherForecast.WeatherForecast;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.client.ClientBuilder;
import java.io.IOException;

/**
 * Created by Влад on 08.01.2016.
 */
public class WeatherForecastImpl implements WeatherForecast {

    @Override
    public void getWeatherForecast(HttpServletRequest request) throws IOException {
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
    }
}
