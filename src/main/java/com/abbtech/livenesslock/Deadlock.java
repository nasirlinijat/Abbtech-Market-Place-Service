package com.abbtech.livenesslock;

public class Deadlock {
    private Object objectOne = new Object();
    private Object objectTwo = new Object();


    public void doSomethingOnObjectOne() {
        System.out.println("Object One is doing something");
        synchronized (objectOne) {
            System.out.println("Object One started doing something");
            synchronized (objectTwo) {
                System.out.println("Object One is waiting for object two");
            }
        }
    }

    public void doSomethingOnObjectTwo() {
        System.out.println("Object Two is doing something");
        synchronized (objectTwo) {
            System.out.println("Object Two started doing something");
            synchronized (objectOne) {
                System.out.println("Object Two is waiting for object one");
            }
        }
    }
}

class DeadlockTest {
    static void main() {
        Deadlock deadlock = new Deadlock();
        Thread threadOne = new Thread(() -> deadlock.doSomethingOnObjectOne());
        Thread threadTwo = new Thread(() -> deadlock.doSomethingOnObjectTwo());
        threadOne.start();
        threadTwo.start();

    }
}


