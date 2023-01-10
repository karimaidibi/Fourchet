package com.fourchet.bl.review;

import com.fourchet.bl.recipe.RecipeFacade;
import com.fourchet.persist.AbstractFactory;
import com.fourchet.persist.recipe.RecipeDao;
import com.fourchet.persist.review.ReviewDao;
import com.fourchet.persist.review.ReviewDaoMongoDB;
import com.fourchet.recipe.Recipe;
import com.fourchet.review.Review;
import com.fourchet.users.User;

import java.util.List;

public class ReviewFacade {
    private static ReviewFacade instance = null;
    private AbstractFactory abstractFactory;

    private ReviewDao reviewDao;

    private User currentUser;

    private ReviewFacade() {
        this.abstractFactory = AbstractFactory.getInstance();
        this.reviewDao = abstractFactory.getReviewDao();
    }

    public static ReviewFacade getInstance() {
        if (instance == null) {
            instance = new ReviewFacade();
        }
        return instance;
    }

    public Review save(Review review) throws Exception
    {
        try {
            reviewDao.save(review);
        } catch (Exception e){
            throw new Exception("Error during the connection to the database");
        }
        return review;
    }

    public Review update(Review oldReview, Review newReview)
    {
        reviewDao.updateReview(oldReview,newReview);
        return newReview;
    }

    public void delete(Review review)
    {
        reviewDao.delete(review);
    }

    public List<Review> getAll() {
        return reviewDao.getAll();
    }

    public List<Review> findAllByReviewed(String IDReviewed){
        return reviewDao.findAllByReviewed(IDReviewed);
    }

    public Review findOneReview(String IDReviewed, String IDReviewer){
        return reviewDao.findOneReview(IDReviewed,IDReviewer);
    }

    /*
    public static void main(String[] args) throws Exception {
        ReviewFacade reviewFacade = ReviewFacade.getInstance();
        Review review = new Review(1,"comme,t","test@gmail.com","test");
        reviewFacade.save(review);
        reviewFacade.getAll();
    }

     */
}
