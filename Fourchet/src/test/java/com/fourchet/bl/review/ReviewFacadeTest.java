package com.fourchet.bl.review;

import com.fourchet.review.Review;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReviewFacadeTest {

    @Test
    void save() {
        ReviewFacade reviewFacade = ReviewFacade.getInstance();
        Review review = new Review(4, "Bien", "qrgqerg", "qergergerg");
        try {
            reviewFacade.save(review);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void update() {
        ReviewFacade reviewFacade = ReviewFacade.getInstance();
        Review oldreview = new Review(4, "Bien", "qrgqerg", "qergergerg");
        Review newreview = new Review(4, "Très Bien", "qrgqerg", "qergergerg");
        try {
            reviewFacade.update(oldreview, newreview);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void findAllByReviewed() {
        ReviewFacade reviewFacade = ReviewFacade.getInstance();
        try {
            reviewFacade.findAllByReviewed("qergergerg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}