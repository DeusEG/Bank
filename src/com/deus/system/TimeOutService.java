package com.deus.system;

import java.util.Random;
import java.util.concurrent.Callable;

public class TimeOutService implements Callable<Void> {
    private final BackSystem backSystem;

    public TimeOutService(BackSystem backSystem) {
        this.backSystem = backSystem;
    }

    @Override
    public Void call() {
        int maxTimeOut = 10000;
        int minTimeOut = 5000;
        try {
            Thread.sleep(new Random().nextInt(maxTimeOut) + minTimeOut);
            backSystem.setBalance(new Random().nextInt(10000) + 5000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
