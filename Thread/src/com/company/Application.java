package com.company;

public class Application {
    public static void main(String[] args) {
//        System.out.println("extends Thread class");
//        Printer printer = new Printer();
//        printer.setDaemon(true);
//        printer.start();
//        for (int i = 0;i<=10;i++){
//            System.out.println("Main"+i+" "+Thread.currentThread().getName());
//        }

//        System.out.println("implement Runnable interface");
        Counter counter = new Counter();

//        System.out.println("Before Main P: "+Thread.currentThread().getPriority());
//
//        Thread.currentThread().setPriority(1);

        Thread thread = new Thread(counter);
        //Thread.currentThread().setPriority(2);
//        thread.setPriority(10);
        //thread.setPriority(11);

//        System.out.println("Main P: "+Thread.currentThread().getPriority());
//        System.out.println("Child P: "+thread.getPriority());
        thread.start();
        thread.interrupt();
//        try {
//            //thread.join();
//            //thread.join(1000);
//            thread.join(1000,350);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        for (int i = 0;i<=100;i++){
            System.out.println("Main"+i+" "+Thread.currentThread().getName());
        }
        //thread.interrupt();


//          System.out.println("Thread class constructors");
//        Thread thread1 = new Thread();
//        Thread thread2 = new Thread(counter);
//        Thread thread3 = new Thread("thread");
//        Thread thread4 = new Thread(counter,"thread");
//        Thread thread5 = new Thread(new ThreadGroup(),"thread");
//        Thread thread6 = new Thread(new ThreadGroup(),counter);
//        Thread thread7 = new Thread(new ThreadGroup(),"thread",counter);
//        Thread thread8 = new Thread(new ThreadGroup(),"thread",counter,34);

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
}
