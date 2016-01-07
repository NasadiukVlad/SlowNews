package com.slownews.dao;

import com.slownews.domain.Users;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Влад on 03.01.2016.
 */
public class UsersJpaDao implements UsersDao {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction transaction;

    public UsersJpaDao() {
        entityManagerFactory = Persistence.createEntityManagerFactory("SlowNewsPersistance");
        entityManager = entityManagerFactory.createEntityManager();
        transaction = entityManager.getTransaction();
    }

    @Override
    public void addUser(Users user) {
        transaction.begin();
        entityManager.persist(user);
        transaction.commit();
    }

    @Override
    public List<Users> getAll() {
        List<Users> allUsersList = new ArrayList<>();

        transaction.begin();
        TypedQuery<Users> allUsersQueryResult =
                entityManager.createQuery("SELECT users FROM Users users", Users.class);
        transaction.commit();
        allUsersList = allUsersQueryResult.getResultList();
        return allUsersList;
    }

    @Override
    public Users getByLogin(String username) {
        List<Users> allUsersList = new ArrayList<>();

        transaction.begin();
        TypedQuery<Users> result =
                entityManager.createQuery("SELECT users FROM Users users where users.username = '" + username + "'",
                        Users.class);
        transaction.commit();

        allUsersList = result.getResultList();

       if (allUsersList.isEmpty()) {
            return null;
        } else {
            return allUsersList.get(0);
        }

    }
}


