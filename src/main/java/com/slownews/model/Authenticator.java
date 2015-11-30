package com.slownews.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Влад on 15.11.2015.
 */
public class Authenticator {
    public String authenticate(Map<String, User> users, String username, String password) {


        if (!username.isEmpty() && !password.isEmpty() && users.get(username).getUsername() != null && users.get(username).getUsername().equals(username) && users.get(username).getPassword().equals(password)) {
            return "userCanLogin";
        } else {
            return "UserNotExist";
        }

    }
}

       /* Map<String, String> userList =  User.getInstance().getUsers();

        for (Map.Entry<String, String> entry : userList.entrySet()) {
            String usernameInList = entry.getKey();
            String userPassword = entry.getValue();

            //if (!usernameInList.equals(username) ) {

               return "success";
           // }

        }
    //    userList.containsKey(username);


        return "success";
    }*/

