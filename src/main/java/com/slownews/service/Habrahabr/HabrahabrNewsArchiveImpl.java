package com.slownews.service.Habrahabr;

import com.slownews.dao.Habrahabr.HabrahabrArchiveJpaDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Влад on 08.01.2016.
 */
public class HabrahabrNewsArchiveImpl implements HabrahabrNewsArchive {
    @Override
    public ArrayList getHabrahabrNewsArchive() {
        HabrahabrArchiveJpaDao habrahabrNewsArchiveJpaDao = new HabrahabrArchiveJpaDao();

        List<com.slownews.domain.Habrahabr.HabrahabrNewsArchive> news = habrahabrNewsArchiveJpaDao.getAll();

        ArrayList archive = new ArrayList();

        for (int i = 0; i < news.size(); i++) {
            HashMap items = new HashMap();
            items.put("title", news.get(i).getTitle());
            items.put("description", news.get(i).getDescription());
            items.put("link", news.get(i).getLink());
            archive.add(items);
        }
        return archive;
    }
}
