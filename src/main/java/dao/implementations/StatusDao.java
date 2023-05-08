package dao.implementations;

import dao.interfaces.StatusInterface;
import entities.Status;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class StatusDao implements StatusInterface {
    private final EntityManager em;
    private static final Logger logger = LoggerFactory.getLogger(StatusDao.class);
    public StatusDao(EntityManager em){
        this.em = em;
    }

    @Override
    public Status findById(long statusId) {
        return em.find(Status.class, statusId);
    }

    @Override
    public Status findByName(String name){
        return em.createQuery("SELECT s FROM Status s WHERE s.name = :name", Status.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public List<Status> findAll() {
        return em.createQuery("SELECT s FROM Status s", Status.class).getResultList();
    }

    @Override
    public Status create(Status status) {
        em.persist(status);
        return status;
    }

    @Override
    public void delete(long statusId) {
        em.remove(em.find(Status.class, statusId));
    }
}
