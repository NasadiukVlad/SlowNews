package com.slownews.service.BBC;

import com.slownews.dao.BBC.ArchiveJpaDao;
import com.slownews.domain.BBC.NewsArchive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Влад on 08.01.2016.
 */
public class BBCNewsArchiveImpl implements BBCNewsArchive {
    @Override
    public ArrayList getBBCArchive() {
        ArchiveJpaDao archiveJpaDao = new ArchiveJpaDao();
        NewsArchive newsArchive = new NewsArchive();
        List<NewsArchive> news = archiveJpaDao.getAll();

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
