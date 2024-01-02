package com.deus;

import com.deus.request.Request;
import com.deus.request.RequestHandler;
import com.deus.request.RequestType;
import com.deus.system.BackSystem;
import com.deus.system.FrontSystem;
import com.deus.client.Client;
import com.deus.system.TimeOutService;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        var backSystem = new BackSystem();
        var frontSystem = new FrontSystem();
        var executorTimeOutService = Executors.newFixedThreadPool(3);
        var executorSystem = Executors.newCachedThreadPool();

        var timeOutService = new ArrayList<>(List.of(
                new TimeOutService(backSystem),
                new TimeOutService(backSystem),
                new TimeOutService(backSystem)
        ));

        var requestHandler1 = new RequestHandler("Обработчик заявок №1",
                frontSystem, backSystem);
        var requestHandler2 = new RequestHandler("Обработчик заявок №2",
                frontSystem, backSystem);

        var client1 = new Client("Клиент №1",
                new Request("Клиент №1", 15000, RequestType.REPAYMENT), frontSystem);
        var client2 = new Client("Клиент №2",
                new Request("Клиент №2", 20000, RequestType.CREDIT), frontSystem);
        var client3 = new Client("Клиент №3",
                new Request("Клиент №3", 20000, RequestType.REPAYMENT), frontSystem);
        var client4 = new Client("Клиент №4",
                new Request("Клиент №4", 20000, RequestType.CREDIT), frontSystem);
        var client5 = new Client("Клиент №5",
                new Request("Клиент №5", 20000, RequestType.CREDIT), frontSystem);

        try {
            executorTimeOutService.invokeAll(timeOutService);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        backSystem.getBalance();
        executorTimeOutService.shutdown();

        executorSystem.submit(requestHandler1);
        executorSystem.submit(requestHandler2);
        executorSystem.submit(client1);
        executorSystem.submit(client2);
        executorSystem.submit(client3);
        executorSystem.submit(client4);
        executorSystem.submit(client5);
    }
}
