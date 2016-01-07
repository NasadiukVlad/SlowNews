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
        int n = 0;
       /* TypedQuery<NewsArchive> resultArchive =
                entityManager.createQuery("SELECT title, COUNT(title) AS NumOccurrences FROM NewsArchive archiveNews", NewsArchive.class);*/
        TypedQuery<JavaWorldNewsArchive> result = null;
        result = entityManager.createQuery("SELECT javaWorldNewsArchive FROM JavaWorldNewsArchive javaWorldNewsArchive where javaWorldNewsArchive.link = '" + link + "'",
                JavaWorldNewsArchive.class);

        //   n = resultArchive.getMaxResults();
        linkList = result.getResultList();

    //    for(JavaWorldNewsArchive javaWorldNewsArchiveResult: linkList) {
            System.out.println("link: " + link);
      //      System.out.println("result: " + javaWorldNewsArchiveResult.getLink());
       //     if (!link.equals(javaWorldNewsArchiveResult.getLink())) {
                transaction.begin();
                entityManager.persist(javaWorldNewsArchive);
                transaction.commit();

       //     } else {
                System.out.println("duplicated");
      //      }
     //   }

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
