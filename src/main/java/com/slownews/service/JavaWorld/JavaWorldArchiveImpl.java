package com.slownews.service.JavaWorld;

import com.slownews.dao.JavaWorld.JavaWorldArchiveJpaDao;
import com.slownews.domain.JavaWorld.JavaWorldNewsArchive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Влад on 08.01.2016.
 */
public class JavaWorldArchiveImpl implements JavaWorldArchive {
    @Override
    public ArrayList getJavaWorldNewsArchive() {
        JavaWorldArchiveJpaDao javaWorldNewsArchiveJpaDao = new JavaWorldArchiveJpaDao();

        List<JavaWorldNewsArchive> news = javaWorldNewsArchiveJpaDao.getAll();

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
