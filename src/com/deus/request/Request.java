package com.deus.request;

public class Request {
    private final String clientThreadName;
    private final int amount;
    private final RequestType requestType;

    public Request(String clientThreadName, int amount, RequestType requestType) {
        this.clientThreadName = clientThreadName;
        this.amount = amount;
        this.requestType = requestType;
    }
    public String getClientName() {
        return clientThreadName;
    }

    public int getAmount() {
        return amount;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    @Override
    public String toString() {
        return ": Заявка{" +
                "clientThreadName='" + clientThreadName + '\'' +
                ", amount=" + amount +
                ", requestType=" + requestType +
                '}';
    }
}
