package com.fourchet.review;

import com.fourchet.users.User;

public class Review {
    private double rate;

    private User reviewer;

    //private Recipe recipe;
    //private Dishe dish;
    private String comment;

    public Review(double rate, String comment, User reviewer) {
        this.rate = rate;
        this.comment = comment;
        this.reviewer = reviewer;
        //this.recipe = recipe;
        //this.dish = null;
    }

    /*
    public Review(double rate, String comment, User reviewer) {
        this.rate = rate;
        this.comment = comment;
        this.reviewer = reviewer;
        this.recipe = null;
        this.dish = dish;
    }

     */


    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
