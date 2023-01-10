package com.fourchet.persist.payments;

import com.fourchet.orders.payments.Payment;
import com.fourchet.persist.Dao;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Optional;

public class PaymentDao implements Dao<Payment> {

    @Override
    public Optional<Payment> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Payment> getAll() {
        return null;
    }

    @Override
    public void save(Payment payment) {

    }

    @Override
    public void update(Payment payment, String[] params) {

    }

    @Override
    public void delete(Payment payment) {

    }

    public ObjectId savePayment(Payment payment) {
        return null;
    }
}
