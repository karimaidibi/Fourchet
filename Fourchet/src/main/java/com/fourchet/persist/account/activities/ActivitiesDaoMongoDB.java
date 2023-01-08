package com.fourchet.persist.account.activities;

import com.fourchet.persist.DaoFactory;
import com.fourchet.users.User;
import com.fourchet.users.actitvities.Activity;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


public class ActivitiesDaoMongoDB extends ActivitiesDao {

    // the user dao knows the factory that created it
    DaoFactory factory;

    // get the collection of users
    MongoCollection activitiesCollection;

    // the connection to the database
    MongoDatabase mongoDatabase;

    public ActivitiesDaoMongoDB(DaoFactory factory) {
        this.factory = factory;
        this.mongoDatabase = factory.getMongoDatabase();
        this.activitiesCollection = mongoDatabase.getCollection("activities");
        System.out.println("Collection activities selected successfully");
    }

    @Override
    public Optional<Activity> get(long id) {
        //return Optional.ofNullable(users.get((int) id));
        return null;
    }

    /**
     * return all the users in the database as a list of users objects
     * it should replace the FindIterable<Document> by List<User> or add the founded users to the list of users
     */
    @Override
    public List<Activity> getAll() {
        List activities = new ArrayList<Activity>();
        FindIterable<Document> documents = activitiesCollection.find();
        // Iterate through the documents
        for (Document doc : documents) {
            activities.add(new Activity(doc));
        }
        return activities;
    }

    @Override
    public List<Activity> getAllByUser(User user) {
        List activities = new ArrayList<Activity>();
        Document query = new Document("ownerEmail", user.getEmail());
        FindIterable<Document> documents = activitiesCollection.find(query);
        // Iterate through the documents
        for (Document doc : documents) {
            activities.add(new Activity(doc));
        }
        return activities;
    }

    @Override
    public void save(Activity activity){
        Document activityDocument = new Document("ownerEmail", activity.getOwnerEmail())
                .append("name", activity.getName())
                .append("type", activity.getType())
                .append("location", activity.getLocation())
                .append("phoneNumber", activity.getPhoneNumber())
                .append("picture", activity.getPicture());

        // insert a user into the collection of users
        activitiesCollection.insertOne(activityDocument);
    }

    //
    @Override
    public void update(Activity activity, String[] params) {
    }

    @Override
    public Activity update(Activity activity, String[] params, Object picture) {
        activitiesCollection.updateOne(
                Filters.eq("ownerEmail", activity.getOwnerEmail()),
                Updates.combine(
                        Updates.set("name", params[0]),
                        Updates.set("type", params[1]),
                        Updates.set("location", params[2]),
                        Updates.set("phoneNumber", params[3]),
                        Updates.set("picture", params[4]),
                        Updates.set("picture", picture)
                ));

        // Retrieve the updated user document from the database
        Document updatedActivityDoc = (Document) activitiesCollection.find(Filters.eq("name", params[1])).first();
        // Create a new User object from the updated user document
        Activity updatedActivity = new Activity(updatedActivityDoc);
        return updatedActivity;
    }


    @Override
    public void delete(Activity activity) {
        //delete the user
        //document to delete
        Document activityToDelete = new Document("ownerEmail", activity.getOwnerEmail())
                .append("name", activity.getName())
                .append("type", activity.getType())
                .append("location", activity.getLocation())
                .append("phoneNumber", activity.getPhoneNumber());

        activitiesCollection.deleteOne(activityToDelete);
    }

    @Override
    public Activity findByName(User user, String name) {
        Document query = new Document("ownerEmail", user.getEmail())
                .append("name", name);
        FindIterable<Document> docs = activitiesCollection.find(query);
        if (docs.first() == null) {
            return null;
        }
        return new Activity(docs.first());
    }

}