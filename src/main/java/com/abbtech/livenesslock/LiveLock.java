package com.abbtech.livenesslock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LiveLock {
    private Boolean flagOne = true;
    private Boolean flagTwo = true;


    public void doSomethingMethodOne() {
        System.out.println("Started something method one");
        while (flagOne) {
            System.out.println("Doing something method one");
        }
        System.out.println("Finished something method one");
        flagTwo = false;
    }

    public void doSomethingMethodTwo() {
        System.out.println("Started something method two");
        while (flagTwo) {
            System.out.println("Doing something method two");
        }
        System.out.println("Finished something method two");
        flagOne = false;
    }

}

class LiveLockTest {
    static void main() {

        LiveLock liveLock = new LiveLock();
        Thread threadOne = new Thread(() -> liveLock.doSomethingMethodOne());
        Thread threadTwo = new Thread(() -> liveLock.doSomethingMethodTwo());


        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(threadOne);
        executorService.execute(threadTwo);
    }
}
