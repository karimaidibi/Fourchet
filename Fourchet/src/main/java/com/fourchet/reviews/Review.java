package com.fourchet.reviews;

public class Review {
    private double rate;

    private String comment;

    public Review(double rate, String comment) {
        this.rate = rate;
        this.comment = comment;
    }

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
