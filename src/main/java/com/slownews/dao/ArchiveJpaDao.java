package com.slownews.dao;

import com.slownews.domain.NewsArchive;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Влад on 03.01.2016.
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
        int n = 0;
       /* TypedQuery<NewsArchive> resultArchive =
                entityManager.createQuery("SELECT title, COUNT(title) AS NumOccurrences FROM NewsArchive archiveNews", NewsArchive.class);*/
        TypedQuery<NewsArchive> result = null;
        result = entityManager.createQuery("SELECT archiveNews FROM NewsArchive archiveNews where archiveNews.link = '" + link + "'",
                NewsArchive.class);

     //   n = resultArchive.getMaxResults();
        linkList = result.getResultList();

        for(NewsArchive newsArchiveResult: linkList) {
            System.out.println("link: " + link);
            System.out.println("result: " + newsArchiveResult.getLink());
            if (!link.equals(newsArchiveResult.getLink())) {
                transaction.begin();
                entityManager.persist(newsArchive);
                transaction.commit();

            } else {
                System.out.println("duplicated");
            }
        }

    }

    @Override
    public List<NewsArchive> getAll() {
        List<NewsArchive> archiveListResult = new ArrayList<>();

        transaction.begin();
        TypedQuery<NewsArchive> resultArchive =
                entityManager.createQuery("SELECT archiveNews FROM NewsArchive archiveNews", NewsArchive.class);
        transaction.commit();
        archiveListResult = resultArchive.getResultList();
        return archiveListResult;
    }
}
