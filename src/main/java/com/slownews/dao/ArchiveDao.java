package com.slownews.dao;

import com.slownews.domain.NewsArchive;

import java.util.List;

/**
 * Created by ���� on 03.01.2016.
 */
public interface ArchiveDao {
    void create(NewsArchive newsArchive);
    List<NewsArchive> getAll();
}
