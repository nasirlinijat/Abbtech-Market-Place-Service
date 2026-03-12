package com.abbtech.designpatterns.singleton;

public enum SingletonVersionThree {
    FRIDAY {
        @Override
        public void run() {

        }
    }, INSTANCE2 {
        @Override
        public void run() {

        }
    };


    public abstract void run();
}
