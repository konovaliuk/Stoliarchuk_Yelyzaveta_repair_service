package org.stoliarchuk.dao.interfaces;

import org.stoliarchuk.entities.Feedback;

import java.util.List;

public interface FeedbackInterface {
    Feedback findById(long feedback_id);
    List<Feedback> findAll();
    List<Feedback> findAllByRequestId(long request_id);
    void createFeedback(Feedback feedback);
    void deleteById(long feedback_id);
}
