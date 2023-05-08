package dao.implementations;

import dao.interfaces.FeedbackInterface;
import entities.Feedback;
import entities.Request;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class FeedbackDao implements FeedbackInterface {
    private final EntityManager em;
    private static final Logger logger = LoggerFactory.getLogger(Feedback.class);
    public FeedbackDao(EntityManager em){
        this.em = em;
    }

    @Override
    public Feedback findById(long feedbackId) {
        return em.find(Feedback.class, feedbackId);
    }

    @Override
    public List<Feedback> findAll() {
        return em.createQuery("SELECT f FROM Feedback f", Feedback.class).getResultList();
    }

    @Override
    public List<Feedback> findAllByRequestId(long requestId) {
        return em.createQuery("SELECT f FROM Feedback f WHERE f.request.requestId = :requestId", Feedback.class)
                .setParameter("requestId", requestId)
                .getResultList();
    }

    @Override
    public Feedback create(Feedback feedback) {
        em.persist(feedback);
        return feedback;
    }

    @Override
    public void delete(long feedbackId) {
        em.remove(em.find(Feedback.class, feedbackId));
    }
}
