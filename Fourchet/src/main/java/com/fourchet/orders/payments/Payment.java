package com.fourchet.orders.payments;

import org.bson.Document;

import java.util.HashMap;

public class Payment {

    private PaymentMethod paymentMethod;

    private String userEmail;
    private float orderPrice;
    private float deliveryPrice;
    private float totalPrice;

    public Payment(String userEmail,float orderPrice, float deliveryPrice, float totalPrice) {
        this.userEmail = userEmail;
        this.orderPrice = orderPrice;
        this.deliveryPrice = deliveryPrice;
        this.totalPrice = totalPrice;
    }

    public void addPaymentMethod(String paymentType, String cardNumber, String cardHolderName, String expirationDate, String cvv){
        HashMap<String, String> paymentInfos = new HashMap<>();
        paymentInfos.put("cardNumber", cardNumber);
        paymentInfos.put("cardHolderName", cardHolderName);
        this.paymentMethod = (new PaymentMethod(paymentType, paymentInfos));
    }

    public Document toDocument() {
        Document doc = new Document();
        doc.append("userEmail", this.userEmail);
        doc.append("orderPrice", this.orderPrice);
        doc.append("deliveryPrice", this.deliveryPrice);
        doc.append("totalPrice", this.totalPrice);
        doc.append("paymentMethod", this.paymentMethod.toDocument());
        return doc;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public double getDeliveryPrice() {
        return deliveryPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public PaymentMethod getPaymentMethod() {
        return this.paymentMethod;
    }
}
