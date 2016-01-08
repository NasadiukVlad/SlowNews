package com.slownews.dao;

import com.slownews.domain.HabrahabrNewsArchive;
import com.slownews.domain.NewsArchive;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Влад on 06.01.2016.
 */
public class ArchiveJpaDao implements ArchiveDao {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction transaction;

    public ArchiveJpaDao() {
        entityManagerFactory = Persistence.createEntityManagerFactory("SlowNewsPersistance");
        entityManager = entityManagerFactory.createEntityManager();
        transaction = entityManager.getTransaction();
    }

    @Override
    public void create(NewsArchive newsArchive) {
        String link = newsArchive.getLink();
        List<NewsArchive> linkList = new ArrayList<>();

        TypedQuery<NewsArchive> result = null;
        result = entityManager.createQuery("SELECT newsArchive FROM NewsArchive newsArchive",
                NewsArchive.class);

        linkList = result.getResultList();

        boolean isExist = false;
        for (NewsArchive newsArchiveResult : linkList) {
            String resultString = newsArchiveResult.getLink();
            if (link.equals(resultString)) {
                isExist = true;
            }
        }

        if (!isExist) {
            transaction.begin();
            entityManager.persist(newsArchive);
            transaction.commit();
            System.out.println("added");

        } else {
            System.out.println("Duplicate");
        }

    }

    @Override
    public List<NewsArchive> getAll() {
        List<NewsArchive> archiveListResult = new ArrayList<>();

        transaction.begin();
        TypedQuery<NewsArchive> resultArchive =
                entityManager.createQuery("SELECT newsArchive FROM NewsArchive newsArchive", NewsArchive.class);
        transaction.commit();
        archiveListResult = resultArchive.getResultList();
        return archiveListResult;
    }
}
