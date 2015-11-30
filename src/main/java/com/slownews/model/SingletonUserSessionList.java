package com.slownews.model;

/**
 * Created by Влад on 14.11.2015.
 */
public class SingletonUserSessionList {

    private static SingletonUserSessionList instance;

    public static SingletonUserSessionList getInstance() {
        if(instance == null) {
            instance = new SingletonUserSessionList();
        }
        return instance;
    }

    private SingletonUserSessionList() {
    }
}
