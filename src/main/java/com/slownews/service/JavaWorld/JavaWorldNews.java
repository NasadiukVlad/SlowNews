package com.slownews.service.JavaWorld;

import java.io.IOException;
import java.util.List;

/**
 * Created by Влад on 08.01.2016.
 */
public interface JavaWorldNews {
    List<com.slownews.model.JavaWorld.JavaWorldNews> getJavaWorldNews() throws IOException;
}
