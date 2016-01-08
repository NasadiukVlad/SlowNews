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

        String link = habrahabrNewsArchive.getLink();
        List<HabrahabrNewsArchive> linkList = new ArrayList<>();

        TypedQuery<HabrahabrNewsArchive> result = null;
        result = entityManager.createQuery("SELECT habrahabrNewsArchive FROM HabrahabrNewsArchive habrahabrNewsArchive",
                HabrahabrNewsArchive.class);

        linkList = result.getResultList();

        boolean isExist = false;
        for (HabrahabrNewsArchive newsArchiveResult : linkList) {
            String resultString = newsArchiveResult.getLink();
            if (link.equals(resultString)) {
                isExist = true;
            }
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
