package com.fourchet.bl.orders.payments;

import com.fourchet.bl.orders.CartFacade;
import com.fourchet.orders.Cart;
import com.fourchet.orders.payments.Payment;
import com.fourchet.persist.AbstractFactory;
import com.fourchet.persist.payments.PaymentDao;
import com.fourchet.users.User;

//this class is a singleton class (we create the facade only once)
public class PaymentFacade {
    private static PaymentFacade instance = null;

    private AbstractFactory abstractFactory;
    private PaymentDao paymentDao;
    private Payment payment;

    private PaymentFacade() {
        this.abstractFactory = AbstractFactory.getInstance();
        this.paymentDao = abstractFactory.getPaymentDao();
    }

    public static PaymentFacade getInstance() {
        if (instance == null) {
            instance = new PaymentFacade();
        }
        return instance;
    }

    public void pay(float orderPrice, float deliveryPrice, float totalPrice, String paymentType, String cardNumber, String cardHolderName, String expirationDate, String cvv) {
        String userEmail = this.getCurrentUser().getEmail();
        Payment payment = new Payment(userEmail,orderPrice, deliveryPrice, totalPrice);
        payment.addPaymentMethod(paymentType, cardNumber, cardHolderName, expirationDate, cvv);
        Object paymentId = this.paymentDao.savePayment(payment);
        System.out.println("Payment id: " + paymentId);
        this.payment = payment;
    }

    public Cart getCart() {
        return CartFacade.getInstance().getCart(this.getCurrentUser());
    }

    public User getCurrentUser() {
        return CartFacade.getInstance().getCurrentUser();
    }

    public void deleteCart() {
        CartFacade.getInstance().deleteCart();
    }

    public Payment getPayment() {
        return this.payment;
    }
}
