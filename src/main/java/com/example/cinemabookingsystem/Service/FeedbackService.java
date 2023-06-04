package com.example.cinemabookingsystem.Service;

import com.example.cinemabookingsystem.Model.Feedback;
import com.example.cinemabookingsystem.Model.Request;
import com.example.cinemabookingsystem.Repository.FeedbackRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class FeedbackService {
    private final FeedbackRepo feedbackRepo;
    private final RequestService requestService;

    public Feedback getFeedback(long id) {
        return feedbackRepo.findById(id).orElse(null);
    }

    public List<Feedback> getFeedbacksByRequestId(long id) {
        List<Feedback> foundFeedbacks = feedbackRepo.findAllByRequestId(id);
        if(foundFeedbacks == null || foundFeedbacks.isEmpty())
            throw new IllegalArgumentException("No such feedbacks");
        return foundFeedbacks;
    }

    public List<Feedback> getAllFeedbacks() {
        return feedbackRepo.findAll();
    }

    public Feedback createFeedback(Long userId, Long requestId, Feedback feedback) {
        Request foundRequest = requestService.getRequest(requestId);
        if(foundRequest == null)
            throw new IllegalArgumentException("Request does not exist");

        feedback.setRequest(foundRequest);
        return feedbackRepo.save(feedback);
    }

    public void updateFeedback(Long id, Feedback feedback) throws IllegalArgumentException {
        Optional<Feedback> maybeFeedbackToUpdate = feedbackRepo.findById(id);
        if(maybeFeedbackToUpdate.isEmpty())
            throw new IllegalArgumentException("Invalid feedback");

        Feedback feedbackToUpdate = maybeFeedbackToUpdate.get();
        if(feedback.getRating() != null)
            feedbackToUpdate.setRating(feedback.getRating());
        if(feedback.getFeedbackDescription() != null && !feedback.getFeedbackDescription().isBlank())
            feedbackToUpdate.setFeedbackDescription(feedback.getFeedbackDescription());

        feedbackRepo.save(feedbackToUpdate);
    }

    public void deleteFeedback(long id) {
        feedbackRepo.deleteById(id);
    }
}