package dao.interfaces;

import entities.Feedback;

import java.util.List;

public interface FeedbackInterface {
    Feedback findById(long feedbackId);
    List<Feedback> findAll();
    List<Feedback> findAllByRequestId(long requestId);
    Feedback create(Feedback feedback);
    void delete(long feedbackId);
}
