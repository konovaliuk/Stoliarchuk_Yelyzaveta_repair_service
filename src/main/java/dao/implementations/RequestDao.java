package dao.implementations;

import dao.interfaces.RequestInterface;
import entities.Request;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RequestDao implements RequestInterface {
    private final EntityManager em;
    private static final Logger logger = LoggerFactory.getLogger(Request.class);
    public RequestDao(EntityManager em){
        this.em = em;
    }

    @Override
    public Request findById(long requestId) {
        return em.find(Request.class, requestId);
    }

    @Override
    public List<Request> findAll() {
        return em.createQuery("SELECT r FROM Request r", Request.class).getResultList();
    }

    @Override
    public List<Request> findAllByUserId(long userId) {
        return em.createQuery("SELECT r FROM Request r WHERE r.user.userId = :userId", Request.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Request> findAllByStatus(String statusName) {
        return em.createQuery("SELECT r FROM Request r WHERE r.status.name = :statusName", Request.class)
                .setParameter("statusName", statusName)
                .getResultList();
    }

    @Override
    public Request create(Request request) {
        em.persist(request);
        return request;
    }

    @Override
    public void update(Request request) {
        em.merge(request);
    }

    @Override
    public void delete(long requestId) {
        em.remove(em.find(Request.class, requestId));
    }
}
