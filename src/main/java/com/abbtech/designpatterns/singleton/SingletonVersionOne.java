package com.abbtech.designpatterns.singleton;

public class SingletonVersionOne {
    public final static SingletonVersionOne instance = new SingletonVersionOne();


    private SingletonVersionOne() {
    }
}
