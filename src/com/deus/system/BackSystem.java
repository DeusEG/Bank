package com.deus.system;

import com.deus.request.Request;
import com.deus.request.RequestType;

public class BackSystem {
    private long balance;

    public BackSystem(long balance) {
        this.balance = balance;
    }
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
    public synchronized void increase(Request request, String handlerName) {
        balance += request.getAmount();
        System.out.println("Бэк система" + request
                        + " УСПЕШНО ВЫПОЛНЕНА. Получена от " + handlerName
                        + ". Баланс банка = " + balance);
    }
    public synchronized void decrease(Request request, String handlerName) {
        var amount = request.getAmount();
        if (balance - amount < 0) {
            System.out.println("Бэк система" + request
                            + " НЕ ВЫПОЛНЕНА. Получена от " + handlerName + ". Сумма больше баланса банка. "
                            + "Баланс банка = " + balance);
        } else {
            balance -= amount;
            System.out.println("Бэк система" + request
                    + " УСПЕШНО ВЫПОЛНЕНА. Получена от " + handlerName
                    + ". Баланс банка = " + balance);
        }
    }
}
