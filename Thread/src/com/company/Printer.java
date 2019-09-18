package com.company;

public class Printer extends Thread {
    @Override
    public void run() {
        for (int i = 0;i<=100;i++){
            System.out.println("child"+i+" "+currentThread().getName());
        }
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void start() {
        //super.start();
        System.out.println("start");
        super.start();
    }

    public void run(int x) {
        for (int i = 0;i<=100000;i++){
            System.out.println("child"+i+" "+currentThread().getName());
        }
    }
}
