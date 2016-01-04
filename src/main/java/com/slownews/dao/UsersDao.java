package com.slownews.dao;

import com.slownews.domain.Users;

import java.util.List;

/**
 * Created by ���� on 03.01.2016.
 */
public interface UsersDao {
    void addUser(Users user);
    List<Users> getAll();
    Users getByLogin(String login);
}
