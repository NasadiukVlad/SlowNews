package com.slownews.dao;

import com.slownews.domain.HabrahabrNewsArchive;
import com.slownews.domain.NewsArchive;

import java.util.List;

/**
 * Created by Влад on 06.01.2016.
 */
public interface HabrahabrArchiveDao {
    void create(HabrahabrNewsArchive habrahabrNewsArchive);
    List<HabrahabrNewsArchive> getAll();
    void close();
}
