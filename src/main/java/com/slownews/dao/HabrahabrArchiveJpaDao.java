package com.slownews.dao;

import com.slownews.domain.HabrahabrNewsArchive;
import com.slownews.domain.NewsArchive;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Влад on 06.01.2016.
 */
public class HabrahabrArchiveJpaDao implements HabrahabrArchiveDao {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction transaction;

    public HabrahabrArchiveJpaDao() {
        entityManagerFactory = Persistence.createEntityManagerFactory("SlowNewsPersistance");
        entityManager = entityManagerFactory.createEntityManager();
        transaction = entityManager.getTransaction();
    }

    @Override
    public void create(HabrahabrNewsArchive habrahabrNewsArchive) {
/*        String link = habrahabrNewsArchive.getLink();
        List<HabrahabrNewsArchive> linkList = new ArrayList<>();
        int n = 0;
       *//* TypedQuery<NewsArchive> resultArchive =
                entityManager.createQuery("SELECT title, COUNT(title) AS NumOccurrences FROM NewsArchive archiveNews", NewsArchive.class);*//*
        TypedQuery<HabrahabrNewsArchive> result = null;
        result = entityManager.createQuery("SELECT habrahabrNewsArchive FROM HabrahabrNewsArchive habrahabrNewsArchive where habrahabrNewsArchive.link = '" + link + "'",
                HabrahabrNewsArchive.class);

        //   n = resultArchive.getMaxResults();
        linkList = result.getResultList();

       for(HabrahabrNewsArchive habrahabrNewsArchiveResult: linkList) {
            System.out.println("link: " + link);
            System.out.println("result: " + habrahabrNewsArchiveResult.getLink());
            if (!link.equals(habrahabrNewsArchiveResult.getLink())) {
                transaction.begin();
                entityManager.persist(habrahabrNewsArchive);
                transaction.commit();


            } else {
                System.out.println("duplicated");
            }
        }*/

        String link = habrahabrNewsArchive.getLink();
        List<HabrahabrNewsArchive> linkList = new ArrayList<>();
        int n = 0;
       /* TypedQuery<NewsArchive> resultArchive =
                entityManager.createQuery("SELECT title, COUNT(title) AS NumOccurrences FROM NewsArchive archiveNews", NewsArchive.class);*/
        TypedQuery<HabrahabrNewsArchive> result = null;
        result = entityManager.createQuery("SELECT habrahabrNewsArchive FROM HabrahabrNewsArchive habrahabrNewsArchive",
                HabrahabrNewsArchive.class);

        //   n = resultArchive.getMaxResults();
        linkList = result.getResultList();

        boolean isExist = false;
        for (HabrahabrNewsArchive newsArchiveResult : linkList) {
            String resultString = newsArchiveResult.getLink();
            if(link.equals(resultString)) {
                isExist = true;
            }
           /* System.out.println("link: " + link);

            System.out.println("ResultString:" + resultString);
            System.out.println("result: " + newsArchiveResult.getLink());
            if (!link.equals(resultString)) {
                transaction.begin();
                entityManager.persist(newsArchive);
                transaction.commit();
                System.out.println("added");

            } else {
                System.out.println("duplicated");
            }*/
        }

        if (!isExist) {
            transaction.begin();
            entityManager.persist(habrahabrNewsArchive);
            transaction.commit();
            System.out.println("added");

        } else {
            System.out.println("Duplicate");
        }
    }

    @Override
    public List<HabrahabrNewsArchive> getAll() {
        List<HabrahabrNewsArchive> archiveListResult = new ArrayList<>();

        transaction.begin();
        TypedQuery<HabrahabrNewsArchive> resultArchive =
                entityManager.createQuery("SELECT habrahabrNewsArchive FROM HabrahabrNewsArchive habrahabrNewsArchive", HabrahabrNewsArchive.class);
        transaction.commit();
        archiveListResult = resultArchive.getResultList();
        return archiveListResult;
    }
}
