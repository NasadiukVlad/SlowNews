package com.slownews.dao;


import com.slownews.domain.JavaWorldNewsArchive;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Влад on 06.01.2016.
 */
public class JavaWorldArchiveJpaDao implements JavaWorldArchiveDao {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction transaction;

    public JavaWorldArchiveJpaDao() {
        entityManagerFactory = Persistence.createEntityManagerFactory("SlowNewsPersistance");
        entityManager = entityManagerFactory.createEntityManager();
        transaction = entityManager.getTransaction();
    }

    @Override
    public void create(JavaWorldNewsArchive javaWorldNewsArchive) {

        String link = javaWorldNewsArchive.getLink();
        List<JavaWorldNewsArchive> linkList = new ArrayList<>();

        TypedQuery<JavaWorldNewsArchive> result = null;
        result = entityManager.createQuery("SELECT javaWorldNewsArchive FROM JavaWorldNewsArchive javaWorldNewsArchive",
                JavaWorldNewsArchive.class);

        linkList = result.getResultList();

        boolean isExist = false;
        for (JavaWorldNewsArchive newsArchiveResult : linkList) {
            String resultString = newsArchiveResult.getLink();
            if (link.equals(resultString)) {
                isExist = true;
            }
        }

        if (!isExist) {
            transaction.begin();
            entityManager.persist(javaWorldNewsArchive);
            transaction.commit();
            System.out.println("added");

        } else {
            System.out.println("Duplicate");
        }
    }

    @Override
    public List<JavaWorldNewsArchive> getAll() {
        List<JavaWorldNewsArchive> archiveListResult = new ArrayList<>();

        transaction.begin();
        TypedQuery<JavaWorldNewsArchive> resultArchive =
                entityManager.createQuery("SELECT javaWorldNewsArchive FROM JavaWorldNewsArchive javaWorldNewsArchive", JavaWorldNewsArchive.class);
        transaction.commit();
        archiveListResult = resultArchive.getResultList();
        return archiveListResult;
    }
}
