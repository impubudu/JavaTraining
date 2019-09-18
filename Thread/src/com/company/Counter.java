package com.company;

public class Counter implements Runnable{
    @Override
    public void run() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0;i<=50;i++){
            //Thread.yield();
            System.out.println("child"+i);

        }
    }
}
