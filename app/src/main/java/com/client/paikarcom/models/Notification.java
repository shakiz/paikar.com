package com.client.paikarcom.models;

public class Notification {
    private int RecordId;
    private String OrderConfirmationText;
    private String OrderConfirmationTime;
    private String InvoiceNumber;
    private int SeenStatus;

    public Notification(int recordId, String orderConfirmationText, String orderConfirmationTime, String invoiceNumber, int seenStatus) {
        RecordId = recordId;
        OrderConfirmationText = orderConfirmationText;
        OrderConfirmationTime = orderConfirmationTime;
        InvoiceNumber = invoiceNumber;
        SeenStatus = seenStatus;
    }

    public int getRecordId() {
        return RecordId;
    }

    public void setRecordId(int recordId) {
        RecordId = recordId;
    }

    public String getOrderConfirmationText() {
        return OrderConfirmationText;
    }

    public void setOrderConfirmationText(String orderConfirmationText) {
        OrderConfirmationText = orderConfirmationText;
    }

    public String getOrderConfirmationTime() {
        return OrderConfirmationTime;
    }

    public void setOrderConfirmationTime(String orderConfirmationTime) {
        OrderConfirmationTime = orderConfirmationTime;
    }

    public String getInvoiceNumber() {
        return InvoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        InvoiceNumber = invoiceNumber;
    }

    public int getSeenStatus() {
        return SeenStatus;
    }

    public void setSeenStatus(int seenStatus) {
        SeenStatus = seenStatus;
    }
}
