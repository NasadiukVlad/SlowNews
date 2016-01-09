package com.slownews.dao.JavaWorld;


import com.slownews.domain.JavaWorld.JavaWorldNewsArchive;

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
    public String create(JavaWorldNewsArchive javaWorldNewsArchive) {

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

            } catch (Exception exception) {
                transaction.rollback();

            } finally {
                close();
            }
            System.out.println("added");
            return "added";
        } else {
            System.out.println("Duplicate");
            return "duplicate";
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
