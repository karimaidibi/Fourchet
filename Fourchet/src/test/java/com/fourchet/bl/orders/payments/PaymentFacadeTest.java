package com.fourchet.bl.orders.payments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentFacadeTest {

    /**
     * this is a test for the pay method of the Payment Facade
     * it asserts that if the current user is null then the pay method wil throw an exception
     */
    @Test
    void pay() {
        PaymentFacade paymentFacade = PaymentFacade.getInstance();
        assertThrows(Exception.class, () -> paymentFacade.pay(0,0,0,"","","","",""));
    }
}