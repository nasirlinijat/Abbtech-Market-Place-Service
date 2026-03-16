package com.abbtech.designpatterns.builder;

public class Main {
    static void main() {

        Computer laptop = Computer.laptopBuilder()
                .cdRoom("CD ROOM")
                .hdd("512 GB")
                .ram("8 GB")
                .build();

        Computer desktop = Computer.desktopBuilder()
                .cdRoom("CD ROOM")
                .mouse("mouse")
                .ups("ups")
                .build();

        System.out.println("Laptop: " + laptop.getCpu());

    }
}
