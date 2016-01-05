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
        transaction.begin();
        entityManager.persist(newsArchive);
        transaction.commit();
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
