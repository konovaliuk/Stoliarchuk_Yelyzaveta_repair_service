package org.stoliarchuk.entities;

import java.sql.Date;

public class Feedback {
    private long feedback_id;
    private long request_id;
    private Date created_at;
    private int rating;
    private String feedback_description;

    public long getFeedback_id() {
        return this.feedback_id;
    }
    public void setFeedback_id(long value) {
        this.feedback_id = value;
    }
    public long getRequest_id() {
        return this.request_id;
    }
    public void setRequest_id(long value) {
        this.request_id = value;
    }
    public Date getCreated_at() {
        return this.created_at;
    }
    public void setCreated_at(Date value) {
        this.created_at = value;
    }
    public int getRating() {
        return this.rating;
    }
    public void setRating(int value) {
        this.rating = value;
    }
    public void setFeedback_description(String value) {
        this.feedback_description = value;
    }
    public String getFeedback_description() {
        return this.feedback_description;
    }

    public void print() {
        System.out.println("{ feedback_id: " + this.feedback_id + ", request_id: " + this.request_id + ", created_at: " + this.created_at + ", rating: " + this.rating + ", feedback_description: " + this.feedback_description + " }");
    }

    public Feedback(long Feedback_id, long Request_id, Date Created_at, int Rating, String Feedback_description) {
        this.feedback_id = Feedback_id;
        this.request_id = Request_id;
        this.created_at = Created_at;
        this.rating = Rating;
        this.feedback_description = Feedback_description;
    }

    public Feedback(long Request_id, int Rating, String Feedback_description) {
        this.request_id = Request_id;
        this.rating = Rating;
        this.feedback_description = Feedback_description;
    }

    public Feedback() {

    }
}
