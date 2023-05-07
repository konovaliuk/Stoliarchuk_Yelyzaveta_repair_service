package org.stoliarchuk.entities;

import java.sql.Date;

public class Feedback {
    private long feedbackId;
    private long requestId;
    private Date createdAt;
    private int rating;
    private String feedbackDescription;

    public long getFeedbackId() {
        return this.feedbackId;
    }
    public void setFeedbackId(long value) {
        this.feedbackId = value;
    }
    public long getRequestId() {
        return this.requestId;
    }
    public void setRequestId(long value) {
        this.requestId = value;
    }
    public Date getCreatedAt() {
        return this.createdAt;
    }
    public void setCreatedAt(Date value) {
        this.createdAt = value;
    }
    public int getRating() {
        return this.rating;
    }
    public void setRating(int value) {
        this.rating = value;
    }
    public void setFeedbackDescription(String value) {
        this.feedbackDescription = value;
    }
    public String getFeedback_description() {
        return this.feedbackDescription;
    }

    public void print() {
        System.out.println("{ feedback_id: " + this.feedbackId + ", request_id: " + this.requestId + ", created_at: " + this.createdAt + ", rating: " + this.rating + ", feedback_description: " + this.feedbackDescription + " }");
    }

    public Feedback(long FeedbackId, long RequestId, Date CreatedAt, int Rating, String FeedbackDescription) {
        this.feedbackId = FeedbackId;
        this.requestId = RequestId;
        this.createdAt = CreatedAt;
        this.rating = Rating;
        this.feedbackDescription = FeedbackDescription;
    }

    public Feedback(long RequestId, int Rating, String FeedbackDescription) {
        this.requestId = RequestId;
        this.rating = Rating;
        this.feedbackDescription = FeedbackDescription;
    }

    public Feedback() {

    }
}
