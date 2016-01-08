package com.slownews.dao.Habrahabr;

import com.slownews.domain.Habrahabr.HabrahabrNewsArchive;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ���� on 06.01.2016.
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
    public String create(HabrahabrNewsArchive habrahabrNewsArchive) {

        String link = habrahabrNewsArchive.getLink();
        List<HabrahabrNewsArchive> linkList = new ArrayList<>();
        boolean isExist = false;

        try {
            transaction.begin();
            TypedQuery<HabrahabrNewsArchive> result = null;
            result = entityManager.createQuery("SELECT habrahabrNewsArchive FROM HabrahabrNewsArchive habrahabrNewsArchive",
                    HabrahabrNewsArchive.class);
            transaction.commit();
            linkList = result.getResultList();

            for (HabrahabrNewsArchive newsArchiveResult : linkList) {
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
                entityManager.persist(habrahabrNewsArchive);
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
    public List<HabrahabrNewsArchive> getAll() {
        List<HabrahabrNewsArchive> archiveListResult = new ArrayList<>();
        try {
            transaction.begin();
            TypedQuery<HabrahabrNewsArchive> resultArchive =
                    entityManager.createQuery("SELECT habrahabrNewsArchive FROM HabrahabrNewsArchive habrahabrNewsArchive", HabrahabrNewsArchive.class);
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
