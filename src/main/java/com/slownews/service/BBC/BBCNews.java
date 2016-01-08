package com.slownews.service.BBC;

import java.io.IOException;
import java.util.List;

/**
 * Created by Влад on 08.01.2016.
 */
public interface BBCNews {
    List<com.slownews.model.BBC.BBCNews> getNews() throws IOException;
}
