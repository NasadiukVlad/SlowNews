package com.slownews.model;

import com.slownews.domain.Users;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Влад on 15.11.2015.
 */
public class Authenticator {
    public String authenticate(Map<String, User> users, String username, String password) {

     /*   EntityManager entityManager = Persistence.createEntityManagerFactory("tutorialPU").createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.persist(car);

        entityManager.getTransaction().commit();

        TypedQuery<Users> query =  entityManager.createQuery("SELECT car FROM Cars car", Users.class);
        List<Cars> results = query.getResultList();

        System.out.println(results.get(0).toString());

        entityManager.close();
*/
        if (!username.isEmpty() && !password.isEmpty() && users.get(username) != null && users.get(username).getUsername().equals(username) && users.get(username).getPassword().equals(password)) {
            return "userCanLogin";
        } else {
            return "UserNotExist";
        }

    }
}

