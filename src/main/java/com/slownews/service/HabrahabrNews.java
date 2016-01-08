package com.slownews.service;

import java.io.IOException;
import java.util.List;

/**
 * Created by Влад on 08.01.2016.
 */
public interface HabrahabrNews {
    List<com.slownews.model.HabrahabrNews> getHabrahabrNews() throws IOException;
}
