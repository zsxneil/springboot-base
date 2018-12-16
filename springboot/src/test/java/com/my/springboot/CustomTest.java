package com.my.springboot;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class CustomTest {

    @Test
    public void nonstaticsyncTest() {
        SyncNonStatic nonStatic = new SyncNonStatic();
        new Thread(() -> {
            new SyncNonStatic().sync();
        }).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        nonStatic.nonSync();
        nonStatic.sync();
    }

    @Test
    public void staticsyncTest() {
        new Thread(() -> {
            SyncStatic.sync();
        }).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SyncStatic.nonSync();
        SyncStatic.sync();
    }
}
