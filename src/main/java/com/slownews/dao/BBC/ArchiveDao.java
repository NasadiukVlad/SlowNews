package com.slownews.dao.BBC;

import com.slownews.domain.BBC.NewsArchive;

import java.util.List;

/**
 * Created by Влад on 03.01.2016.
 */
public interface ArchiveDao {
    String create(NewsArchive newsArchive);
    List<NewsArchive> getAll();
    void close();
}
