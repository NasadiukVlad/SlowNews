package com.slownews.controller;

/**
 * Created by Влад on 25.11.2015.
 */
public class Task2 {
    public static void main(String[] args) {
        String devideBy3 = "fizz";
        String devideBy5 = "buzz";
        String devideBy3And5 = "fizzbuzz";

        for (int i = 1; i <= 100; i++) {
            if (i % 5 == 0 && i % 3 == 0) {
                System.out.println(devideBy3And5);
                i++;
            }
            if (i % 5 == 0) {
                System.out.println(devideBy5);
                i++;
            }
            if (i % 3 == 0) {
                System.out.println(devideBy3);
                i++;
            }

                System.out.println(i);

        }
    }
}
