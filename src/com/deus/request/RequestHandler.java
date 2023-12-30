package com.deus.request;

import com.deus.system.BackSystem;
import com.deus.system.FrontSystem;
public class RequestHandler implements Runnable{
    private final String handlerName;
    private final FrontSystem frontSystem;
    private final BackSystem backSystem;
    public RequestHandler(String handlerName, FrontSystem frontSystem, BackSystem backSystem) {
        this.handlerName = handlerName;
        this.frontSystem = frontSystem;
        this.backSystem = backSystem;
    }

    @Override
    public void run() {
        while (true) {
            var request = frontSystem.getRequest();
            System.out.println(handlerName + ": Получена заявка на обработку по клиенту - "
                    + request.getClientName());

            backSystem.processHandle(request, handlerName);
        }
    }
}
