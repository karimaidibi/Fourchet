package com.fourchet.persist.payments;

import com.fourchet.orders.payments.Payment;
import com.fourchet.persist.DaoFactory;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CreateCollectionOptions;
import org.bson.Document;
import org.bson.types.ObjectId;

public class PaymentDaoMongoDB extends PaymentDao {

    // the user dao knows the factory that created it
    DaoFactory factory;

    // get the collection of payments
    MongoCollection paymentsCollection;

    // the connection to the database
    MongoDatabase mongoDatabase;

    public PaymentDaoMongoDB(DaoFactory factory) {
        this.factory = factory;
        this.mongoDatabase = factory.getMongoDatabase();
        this.paymentsCollection = mongoDatabase.getCollection("payments");
        System.out.println("Collection payments selected successfully");
    }

    /**
     * save a payment in the database
     * it will transform the payment to a document then save it in the payments collection
     * and return the id of the inserted payment
     */
    public ObjectId savePayment(Payment payment) {
        Document paymentDoc = payment.toDocument();
        paymentsCollection.insertOne(paymentDoc);
        return paymentDoc.getObjectId("_id");
    }
}
