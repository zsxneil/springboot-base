package com.my.springboot;

import java.util.concurrent.TimeUnit;

public class SyncNonStatic {


    public synchronized void nonSync() {
        System.out.println("nonstatic nonsync methond running...");
    }

    public synchronized void sync() {
        System.out.println("nonstatic sync methond running...");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("nonstatic sync methond end...");
    }

}
