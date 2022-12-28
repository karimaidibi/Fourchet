package com.fourchet.review;

import org.bson.Document;

public class Review {
    private int note;

    private String IDReviewer;

    private String IDReviewed;

    private String IDReview;

    private String comment;

    public int getNote() {
        return note;
    }

    public String getIDReviewer() {
        return IDReviewer;
    }

    public String getIDReviewed() {
        return IDReviewed;
    }

    public String getIDReview() {
        return IDReview;
    }

    public String getComment() {
        return comment;
    }

    public Review(Document document) {
        this.comment = (String) document.get("comment");
        this.note = (int) document.get("note");
        this.IDReviewer = (String) document.get("IDReviewer");
        this.IDReviewed = (String) document.get("IDReviewed");
    }

    public Review(int note, String comment, String reviewer, String reviewed) {
        this.note = note;
        this.comment = comment;
        this.IDReviewer = reviewer;
        this.IDReviewed = reviewed;
    }




    public void setRate(int rate) {
        this.note = rate;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
