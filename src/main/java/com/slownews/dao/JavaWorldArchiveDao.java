package com.slownews.dao;

import com.slownews.domain.JavaWorldNewsArchive;
import com.slownews.domain.NewsArchive;

import java.util.List;

/**
 * Created by Влад on 06.01.2016.
 */
public interface JavaWorldArchiveDao {
    void create(JavaWorldNewsArchive javaWorldNewsArchive);
    List<JavaWorldNewsArchive> getAll();
    void close();
}
