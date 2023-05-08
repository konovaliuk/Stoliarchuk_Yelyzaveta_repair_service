package dao.implementations;

import dao.interfaces.UserInterface;
import entities.User;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class UserDao implements UserInterface {
    private final EntityManager em;
    private static final Logger logger = LoggerFactory.getLogger(UserDao.class);
    public UserDao(EntityManager em){
        this.em = em;
    }

    @Override
    public User findById(long userId) {
        return em.find(User.class, userId);
    }

    @Override
    public User findByEmail(String email){
        return em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    @Override
    public User findByUsername(String username){
        return em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public User create(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public void update(User user) {
        em.merge(user);
    }

    @Override
    public void delete(long userId) {
        em.remove(em.find(User.class, userId));
    }
}
