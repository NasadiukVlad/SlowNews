package com.slownews.dao.Habrahabr;

import com.slownews.domain.Habrahabr.HabrahabrNewsArchive;

import java.util.List;

/**
 * Created by Влад on 06.01.2016.
 */
public interface HabrahabrArchiveDao {
    void create(HabrahabrNewsArchive habrahabrNewsArchive);
    List<HabrahabrNewsArchive> getAll();
    void close();
}
