package com.fourchet.orders.payments;

import org.bson.Document;

import java.util.HashMap;

public class PaymentMethod {

    private HashMap<String, String> paymentInfos;
    private String paymentMethod;

    public PaymentMethod(String paymentMethod, HashMap<String, String> paymentInfos) {
        this.paymentMethod = paymentMethod;
        this.paymentInfos = paymentInfos;
    }

    public HashMap<String, String> getPaymentInfos() {
        return paymentInfos;
    }

    public void setPaymentInfos(HashMap<String, String> paymentInfos) {
        this.paymentInfos = paymentInfos;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Document toDocument() {
        Document doc = new Document();
        doc.append("paymentMethod", this.paymentMethod);
        doc.append("paymentInfos", this.paymentInfos);
        return doc;
    }
}
