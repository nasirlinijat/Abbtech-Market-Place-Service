package com.abbtech.designpatterns.builder;

public class Computer {
    private String cdRoom;
    private String cpu;
    private String ram;
    private String hdd;
    private String monitor;
    private String ups;
    private String mouse;

    public static DesktopBuilder desktopBuilder() {
        return new DesktopBuilder();
    }

    public static LaptopBuilder laptopBuilder() {
        return new LaptopBuilder();
    }

    public String getCdRoom() {
        return cdRoom;
    }

    public String getCpu() {
        return cpu;
    }

    public String getRam() {
        return ram;
    }

    public String getHdd() {
        return hdd;
    }

    public String getMonitor() {
        return monitor;
    }

    public String getUps() {
        return ups;
    }

    public String getMouse() {
        return mouse;
    }

    private Computer(LaptopBuilder laptopBuilder) {
        this.cpu = laptopBuilder.cpu;
        this.ram = laptopBuilder.ram;
        this.hdd = laptopBuilder.hdd;
    }

    private Computer(DesktopBuilder desktopBuilder) {
        this.cpu = desktopBuilder.cpu;
        this.ram = desktopBuilder.ram;
        this.hdd = desktopBuilder.hdd;
        this.monitor = desktopBuilder.monitor;
        this.ups = desktopBuilder.ups;
        this.mouse = desktopBuilder.mouse;
        this.cdRoom = desktopBuilder.cdRoom;
    }


    public static class DesktopBuilder {
        private String cdRoom;
        private String cpu;
        private String ram;
        private String hdd;
        private String monitor;
        private String ups;
        private String mouse;


        public DesktopBuilder cdRoom(String cdRoom) {
            this.cdRoom = cdRoom;
            return this;
        }

        public DesktopBuilder cpu(String cpu) {
            this.cpu = cpu;
            return this;
        }

        public DesktopBuilder ram(String ram) {
            this.ram = ram;
            return this;
        }

        public DesktopBuilder hdd(String hdd) {
            this.hdd = hdd;
            return this;
        }

        public DesktopBuilder ups(String ups) {
            this.ups = ups;
            return this;
        }

        public DesktopBuilder monitor(String monitor) {
            this.monitor = monitor;
            return this;
        }

        public DesktopBuilder mouse(String mouse) {
            this.mouse = mouse;
            return this;
        }


        public Computer build() {
            return new Computer(this);
        }

    }


    public static class LaptopBuilder {
        private String cpu;
        private String ram;
        private String hdd;

        public LaptopBuilder cdRoom(String cpu) {
            this.cpu = cpu;
            return this;
        }

        public LaptopBuilder ram(String ram) {
            this.ram = ram;
            return this;
        }

        public LaptopBuilder hdd(String hdd) {
            this.hdd = hdd;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }


    }
}
