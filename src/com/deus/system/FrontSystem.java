package com.deus.system;

import com.deus.request.Request;
import java.util.ArrayDeque;

public class FrontSystem {
    private final ArrayDeque<Request> requestsDeque = new ArrayDeque<>(2);

    public synchronized void addRequest(Request request) {
        var requestLimit = 2;
        while (requestsDeque.size() >= requestLimit){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        requestsDeque.add(request);
        notifyAll();
    }
    public synchronized Request getRequest() {
        while (requestsDeque.isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        notifyAll();
        return requestsDeque.poll();
    }
}
