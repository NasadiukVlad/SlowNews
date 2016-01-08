package com.slownews.dao.Users;

import com.slownews.domain.Users.Users;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ���� on 03.01.2016.
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
        try {
            transaction.begin();
            entityManager.persist(user);
            transaction.commit();

        } catch (Exception exception) {
            transaction.rollback();

        } finally {
            close();
        }
    }

    @Override
    public List<Users> getAll() {
        List<Users> allUsersList = new ArrayList<>();

        try {
            transaction.begin();
            TypedQuery<Users> allUsersQueryResult =
                    entityManager.createQuery("SELECT users FROM Users users", Users.class);
            transaction.commit();
            allUsersList = allUsersQueryResult.getResultList();

        } catch (Exception exception) {
            transaction.rollback();

        } finally {
            close();
        }

        return allUsersList;
    }

    @Override
    public Users getByLogin(String username) {
        List<Users> allUsersList = new ArrayList<>();
        try {
            transaction.begin();
            TypedQuery<Users> result =
                    entityManager.createQuery("SELECT users FROM Users users where users.username = '" + username + "'",
                            Users.class);
            transaction.commit();

            allUsersList = result.getResultList();

        } catch (Exception exception) {
            transaction.rollback();

        } finally {
            close();
        }

        if (allUsersList.isEmpty()) {
            return null;
        } else {
            return allUsersList.get(0);
        }

    }

    @Override
    public void close() {
        if (entityManager != null) {
            entityManager.close();
        }
    }
}


