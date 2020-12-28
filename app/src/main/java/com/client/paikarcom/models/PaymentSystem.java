package com.client.paikarcom.models;

public class PaymentSystem {
    private String ProcessText;

    public PaymentSystem(String processText) {
        ProcessText = processText;
    }

    public String getProcessText() {
        return ProcessText;
    }

    public void setProcessText(String processText) {
        ProcessText = processText;
    }
}
