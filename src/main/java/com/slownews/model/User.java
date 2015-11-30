package com.slownews.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Влад on 15.11.2015.
 */
public class User {
    private String username;
    private String password;

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public User(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



        /*private static volatile User instance;

        private Map<String, String> users;

        private User() {
            users = new HashMap<>();
        }

        public synchronized static User getInstance() {
            if (instance == null) {
                instance = new User();
            }
            return instance;

        }

        public synchronized Map<String, String> getUsers() {
            return users;
        }

        public synchronized void setUsers(String username, String password) {
            users.put(username, password);

        }
*/

}

