package com.fourchet.orders.payments;

import java.util.HashMap;

public class PaymentMethod {

    private HashMap<String, String> paymentInfos;
    private String paymentType;

    public PaymentMethod(String paymentType, HashMap<String, String> paymentInfos) {
        this.paymentType = paymentType;
        this.paymentInfos = paymentInfos;
    }

    public HashMap<String, String> getPaymentInfos() {
        return paymentInfos;
    }

    public void setPaymentInfos(HashMap<String, String> paymentInfos) {
        this.paymentInfos = paymentInfos;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

}
