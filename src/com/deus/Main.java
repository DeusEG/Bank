package com.deus;

import com.deus.request.Request;
import com.deus.request.RequestHandler;
import com.deus.request.RequestType;
import com.deus.system.BackSystem;
import com.deus.system.FrontSystem;
import com.deus.client.Client;
public class Main {
    public static void main(String[] args) {
        var backSystem = new BackSystem( 0);
        var frontSystem = new FrontSystem();

        var requestHandler1 = new Thread(new RequestHandler("Обработчик заявок №1",
                frontSystem, backSystem));
        var requestHandler2 = new Thread(new RequestHandler("Обработчик заявок №2",
                frontSystem, backSystem));
        var client1 = new Thread(new Client("Клиент №1",
                new Request("Клиент №1", 5000, RequestType.CREDIT), frontSystem));
        var client2 = new Thread(new Client("Клиент №2",
                new Request("Клиент №2", 10000, RequestType.CREDIT), frontSystem));
        var client3 = new Thread(new Client("Клиент №3",
                new Request("Клиент №3", 15000, RequestType.REPAYMENT), frontSystem));
        var client4 = new Thread(new Client("Клиент №4",
                new Request("Клиент №4", 20000, RequestType.CREDIT), frontSystem));
        var client5 = new Thread(new Client("Клиент №5",
                new Request("Клиент №5", 30000, RequestType.REPAYMENT), frontSystem));

        client1.start();
        client2.start();
        client3.start();
        client4.start();
        client5.start();

        requestHandler1.start();
        requestHandler2.start();
    }
}
