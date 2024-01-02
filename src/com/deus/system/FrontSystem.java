package com.deus.system;

import com.deus.request.Request;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class FrontSystem {
    /**
     * Выбираем ArrayBlockingQueue потому, что есть ёмкость и блокирующие методы
     * на добавление и получение элементов из очереди
     */
    private final BlockingQueue<Request> requestsQueue = new ArrayBlockingQueue<>(2);
    public void addRequest(Request request) {
        try {
            requestsQueue.put(request);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public Request getRequest() {
        try {
            return requestsQueue.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
