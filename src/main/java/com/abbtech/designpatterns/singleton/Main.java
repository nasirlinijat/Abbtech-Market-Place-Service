package com.abbtech.designpatterns.singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    static void main() {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 20; i++) {
            executorService.execute(() -> {
                SingletonVersionThree instance = SingletonVersionThree.FRIDAY;
                System.out.println(instance.hashCode());
            });
        }

        executorService.shutdown();
    }
}
