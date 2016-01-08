package com.slownews.dao.JavaWorld;

import com.slownews.domain.JavaWorld.JavaWorldNewsArchive;

import java.util.List;

/**
 * Created by Влад on 06.01.2016.
 */
public interface JavaWorldArchiveDao {
    String create(JavaWorldNewsArchive javaWorldNewsArchive);
    List<JavaWorldNewsArchive> getAll();
    void close();
}
