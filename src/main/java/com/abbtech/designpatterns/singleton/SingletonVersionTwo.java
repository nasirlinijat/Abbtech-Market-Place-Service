package com.abbtech.designpatterns.singleton;

public class SingletonVersionTwo {

    private SingletonVersionTwo() {

    }

    private volatile static SingletonVersionTwo instance;

    public static SingletonVersionTwo getInstance() {

        if (instance != null) {
            return instance;
        }

        synchronized (SingletonVersionTwo.class) {
            if (instance == null) {
                instance = new SingletonVersionTwo();
            }
        }
        return instance;
    }
}
