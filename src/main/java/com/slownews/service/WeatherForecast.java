package com.slownews.service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by Влад on 08.01.2016.
 */
public interface WeatherForecast {
    void getWeatherForecast(HttpServletRequest request) throws IOException;
}
