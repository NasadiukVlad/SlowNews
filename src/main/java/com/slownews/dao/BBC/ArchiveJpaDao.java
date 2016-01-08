package com.slownews.dao.BBC;

import com.slownews.domain.BBC.NewsArchive;

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
    public String create(NewsArchive newsArchive) {
        String link = newsArchive.getLink();
        List<NewsArchive> linkList = new ArrayList<>();
        boolean isExist = false;
        try {
            transaction.begin();
            TypedQuery<NewsArchive> result = null;
            result = entityManager.createQuery("SELECT newsArchive FROM NewsArchive newsArchive",
                    NewsArchive.class);
            transaction.commit();
            linkList = result.getResultList();

            for (NewsArchive newsArchiveResult : linkList) {
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
                entityManager.persist(newsArchive);
                transaction.commit();

            } catch (Exception exception) {
                transaction.rollback();

            } finally {
                close();
            }
            System.out.println("added");
            return ("added");

        } else {
            System.out.println("Duplicate");
            return ("duplicate");
        }

    }

    @Override
    public List<NewsArchive> getAll() {
        List<NewsArchive> archiveListResult = new ArrayList<>();
        try {
            transaction.begin();
            TypedQuery<NewsArchive> resultArchive =
                    entityManager.createQuery("SELECT newsArchive FROM NewsArchive newsArchive", NewsArchive.class);
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
