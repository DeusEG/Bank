package com.deus.system;

import com.deus.request.Request;
import java.util.concurrent.atomic.AtomicLong;

public class BackSystem {
    private final AtomicLong balance = new AtomicLong(0);


    public void processHandle(Request request, String handlerName) {
        var requestType = request.getRequestType();
        switch (requestType) {
            case REPAYMENT: {
                increase(request, handlerName);
                break;
            }
            case CREDIT: {
                decrease(request, handlerName);
                break;
            }
        }
    }

    public void getBalance() {
        System.out.println("Общий баланс банка - " + balance);
    }

    public synchronized void increase(Request request, String handlerName) {
        balance.getAndUpdate(a -> a + request.getAmount());
        System.out.println("Бэк система" + request
                        + " УСПЕШНО ВЫПОЛНЕНА. Получена от " + handlerName
                        + ". Баланс банка = " + balance);
    }

    public synchronized void decrease(Request request, String handlerName) {
        long amountOfMoney = request.getAmount();
        if (balance.get() - amountOfMoney < 0) {
            System.out.println("Бэк система" + request
                            + " НЕ ВЫПОЛНЕНА. Получена от " + handlerName + ". Сумма больше баланса банка. "
                            + "Баланс банка = " + balance);
        } else {
            balance.getAndUpdate(a -> a - amountOfMoney);
            System.out.println("Бэк система" + request
                    + " УСПЕШНО ВЫПОЛНЕНА. Получена от " + handlerName
                    + ". Баланс банка = " + balance);
        }
    }

    public void setBalance(long amountOfMoney) {
        balance.getAndUpdate(a -> amountOfMoney + a);
        System.out.println("Бэк система: Баланс банка пополнен на " + balance + " и составляет = " + balance.get());
    }
}
