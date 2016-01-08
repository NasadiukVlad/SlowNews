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
        boolean isExist = false;

        try {
            transaction.begin();
            TypedQuery<JavaWorldNewsArchive> result = null;
            result = entityManager.createQuery("SELECT javaWorldNewsArchive FROM JavaWorldNewsArchive javaWorldNewsArchive",
                    JavaWorldNewsArchive.class);
            transaction.commit();
            linkList = result.getResultList();

            for (JavaWorldNewsArchive newsArchiveResult : linkList) {
                String resultString = newsArchiveResult.getLink();
                if (link.equals(resultString)) {
                    isExist = true;
                }
            }

        } catch (Exception exception) {
            transaction.rollback();

        } finally {
            close();
        }

        if (!isExist) {
            entityManagerFactory = Persistence.createEntityManagerFactory("SlowNewsPersistance");
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            try {
                transaction.begin();
                entityManager.persist(javaWorldNewsArchive);
                transaction.commit();
                System.out.println("added");

            } catch (Exception exception) {
                transaction.rollback();

            } finally {
                close();
            }
        } else {
            System.out.println("Duplicate");
        }
    }

    @Override
    public List<JavaWorldNewsArchive> getAll() {
        List<JavaWorldNewsArchive> archiveListResult = new ArrayList<>();
        try {
            transaction.begin();
            TypedQuery<JavaWorldNewsArchive> resultArchive =
                    entityManager.createQuery("SELECT javaWorldNewsArchive FROM JavaWorldNewsArchive javaWorldNewsArchive", JavaWorldNewsArchive.class);
            transaction.commit();
            archiveListResult = resultArchive.getResultList();

        } catch (Exception exception) {
            transaction.rollback();

        } finally {
            close();
        }

        return archiveListResult;
    }

    @Override
    public void close() {
        if (entityManager != null) {
            entityManager.close();
        }
    }
}
