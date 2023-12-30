package com.deus.client;

import com.deus.request.Request;
import com.deus.system.FrontSystem;

public class Client implements Runnable{
    private final String clientThreadName;
    private final Request request;
    private final FrontSystem frontSystem;

    public Client(String clientThreadName, Request request, FrontSystem frontSystem) {
        this.clientThreadName = clientThreadName;
        this.request = request;
        this.frontSystem = frontSystem;
    }

    @Override
    public void run() {
        System.out.println(clientThreadName + request + " отправлена в банк");
        frontSystem.addRequest(request);
    }
}
