package com.slownews.domain.Users;

import javax.persistence.*;

/**
 * Created by Влад on 03.12.2015.
 */


@Entity
public class Users {

    @Id
    // @SequenceGenerator(initialValue=1, allocationSize=1, name="users_seq", sequenceName="users_seq")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "username_id")
    private long usernameId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "userEmail")
    private String userEmail;

    public Users(String username, String password, String userEmail) {
        this.username = username;
        this.password = password;
        this.userEmail = userEmail;
    }

    public Users() {
    }

    public long getUsernameId() {
        return usernameId;
    }

    public void setUsernameId(long usernameId) {
        this.usernameId = usernameId;
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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

}
