package com.fourchet.payments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Payment {

    private PaymentMethod paymentMethod;

    private String userId;
    private float orderPrice;
    private float deliveryPrice;
    private float totalPrice;

    public Payment(float orderPrice, float deliveryPrice, float totalPrice) {
        this.orderPrice = orderPrice;
        this.deliveryPrice = deliveryPrice;
        this.totalPrice = totalPrice;
    }

    private void addPaymentMethod(String paymentType ,String cardNumber, String cardHolderName, String expirationDate, String cvv){
        HashMap<String, String> paymentInfos = new HashMap<>();
        paymentInfos.put("cardNumber", cardNumber);
        paymentInfos.put("cardHolderName", cardHolderName);
        paymentInfos.put("expirationDate", expirationDate);
        paymentInfos.put("cvv", cvv);
        this.paymentMethod = (new PaymentMethod(paymentType, paymentInfos));
    }

}
