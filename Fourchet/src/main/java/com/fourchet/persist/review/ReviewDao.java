package com.fourchet.persist.review;

import com.fourchet.persist.Dao;
import com.fourchet.recipe.Recipe;
import com.fourchet.review.Review;
import com.fourchet.users.User;

import java.util.List;
import java.util.Optional;

public class ReviewDao implements Dao<Review> {
    @Override
    public Optional<Review> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Review> getAll() {
        return null;
    }

    @Override
    public void save(Review review) {

    }

    @Override
    public void update(Review review, String[] params) {}


    @Override
    public void delete(Review review) {
    }

    public void updateReview(Review oldReview, Review newReview){}

    public List<Review> findAllByReviewed(String IDReviewed){
        return null;
    }
    public Review findOneReview(String IDReviewed, String IDReviewer){
        return null;
    }
}
