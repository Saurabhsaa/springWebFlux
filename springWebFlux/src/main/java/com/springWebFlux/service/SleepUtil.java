package com.springWebFlux.service;

public class SleepUtil {

    public static void sleepSeconds(int sec){
        try {
            Thread.sleep(sec*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
