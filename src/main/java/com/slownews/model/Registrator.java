package com.slownews.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Влад on 15.11.2015.
 */
public class Registrator {

    public String register(Map<String, User> users, String username, String password) {


        if (!username.isEmpty() && !password.isEmpty() && users.containsKey(username)) {
            return "UserExist";
        } else {
            return "UserNotExist";
        }
    }



   /* public String register(String username, String password) {
        Map<String, String> userList =  User.getInstance().getUsers();

        for (Map.Entry<String, String> entry : userList.entrySet()) {
            String usernameInList = entry.getKey();
            String userPassword = entry.getValue();

            if (!usernameInList.equals(username) ) {
                User.getInstance().setUsers(username, password);
                return username;
            }

        }


        return "error";
    }*/


}
