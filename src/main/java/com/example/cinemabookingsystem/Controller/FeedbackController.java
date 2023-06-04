package com.example.cinemabookingsystem.Controller;

import com.example.cinemabookingsystem.Model.Feedback;
import com.example.cinemabookingsystem.Model.Request;
import com.example.cinemabookingsystem.Model.Status;
import com.example.cinemabookingsystem.Model.User;
import com.example.cinemabookingsystem.Service.FeedbackService;
import com.example.cinemabookingsystem.Service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feedbacks")
public class FeedbackController {
    private final FeedbackService feedbackService;

    @GetMapping("/{id}")
    public ResponseEntity<Feedback> getFeedback(@PathVariable Long id){
        try {
            final Feedback feedback = feedbackService.getFeedback(id);
            return ResponseEntity.ok(feedback);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Feedback>> getAllFeedbacks(@RequestParam(required = false) Integer requestId){
        if (requestId != null) {
            try {
                final List<Feedback> feedbacks = feedbackService.getFeedbacksByRequestId(requestId);
                return ResponseEntity.ok(feedbacks);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.notFound().build();
            }
        }

        List<Feedback> feedbacks = feedbackService.getAllFeedbacks();
        return ResponseEntity.ok(feedbacks);
    }

    @PostMapping
    public ResponseEntity<Void> createFeedback(@RequestBody Feedback feedback, @RequestParam Long userId, @RequestParam Long requestId){
        try {
            Feedback feedbackAdded = feedbackService.createFeedback(userId, requestId, feedback);
            String userURI = String.format("/feedbacks/%d", feedbackAdded.getId());
            return ResponseEntity.created(URI.create(userURI)).build();
        } catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Void> changeFeedback(@PathVariable Long id, @RequestBody Feedback feedback){
        try{
            feedbackService.updateFeedback(id, feedback);
            return ResponseEntity.noContent().build();
        }
        catch(IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteById(@PathVariable long id){
        feedbackService.deleteFeedback(id);
        return ResponseEntity.noContent().build();
    }
}
