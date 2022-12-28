package com.fourchet.persist.review;

import com.fourchet.persist.DaoFactory;
import com.fourchet.review.Review;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class ReviewDaoMongoDB {

    // the review dao knows the factory that created it
    DaoFactory factory;

    // get the collection of reviews
    MongoCollection reviewsCollection;

    // the connection to the database
    MongoDatabase mongoDatabase;

    public ReviewDaoMongoDB(DaoFactory factory) {
        this.factory = factory;
        this.mongoDatabase = factory.getMongoDatabase();
        this.reviewsCollection = mongoDatabase.getCollection("reviews");
        System.out.println("Collection reviews selected successfully");
    }

    public List<Review> getReviews() {
        return null;
    }


    /**
     * return all the reviews in the database as a list of reviews objects
     * it should replace the FindIterable<Document> by List<Review> or add the founded reviews to the list of reviews
     */

    public List<Review> getAll() {
        List reviews = new ArrayList<Review>();
        // A request to Find all documents in the collection of reviews
        FindIterable<Document> documents = reviewsCollection.find();
        System.out.println("Documents found successfully");
        // Iterate through the documents
        for (Document doc : documents) {
            System.out.println(doc);
            reviews.add(new Review(doc));
        }
        return reviews;
    }


    public void save(Review review) {
        Document reviewDocument = new Document("note", review.getNote())
                .append("IDReviewed",review.getIDReviewed())
                .append("IDReviewer",review.getIDReviewer())
                .append("comment", review.getComment());
        reviewsCollection.insertOne(reviewDocument);
    }


    public void update(Review oldReview, Review newReview) {
        Document oldReviewDocument = new Document("comment", oldReview.getComment())
                .append("note", oldReview.getNote())
                .append("IDReviewer",oldReview.getIDReviewer())
                .append("IDReviewed",oldReview.getIDReviewed());
        Document newReviewDocument = new Document("comment", newReview.getComment())
                .append("note", newReview.getNote())
                .append("IDReviewer",newReview.getIDReviewer())
                .append("IDReviewed",newReview.getIDReviewed());
        reviewsCollection.updateOne(oldReviewDocument, newReviewDocument);
    }

    public void delete(Review review) {
        Document reviewDocument = new Document("comment", review.getComment())
                .append("note", review.getNote())
                .append("IDReviewer",review.getIDReviewer())
                .append("IDReviewed",review.getIDReviewed());
        reviewsCollection.deleteOne(reviewDocument);
    }

    public List<Review> findAllByReviewed(String IDReviewed) {
        List reviews = new ArrayList<Review>();
        Document query = new Document("IDReviewed", IDReviewed);
        FindIterable<Document> documents = reviewsCollection.find(query);

        for (Document doc : documents) {
            System.out.println(doc);
            reviews.add(new Review(doc));
        }

        return reviews;
    }


    public Review findOneReview(String IDReviewed, String IDReviewer) {
        Document filter = new Document("IDReviewer", IDReviewer)
                .append("IDReviewed", IDReviewed);

        Document review = (Document) reviewsCollection.find(filter).first();

        if (review == null) {
            // Aucun document n'a été trouvé
            System.out.println("Aucun review n'a été trouvé avec cet IDReviewer et cet IDReviewed");
            return null;
        } else {
            // Affichez le contenu du document
            System.out.println(review);
            return new Review(review);
        }

    }

    public static void main(String[] args) {
        DaoFactory factory = new DaoFactory();
        ReviewDaoMongoDB reviewDaoMongoDB = new ReviewDaoMongoDB(factory);
        //reviewDaoMongoDB.save(new Review(6, "Super", "zergzergzerg", "izergzerj"));
        //reviewDaoMongoDB.findOneReview("izergzerj", "hsfafh");
        //reviewDaoMongoDB.getAll();
        reviewDaoMongoDB.findAllByReviewed("izergzerj");

    }
}
