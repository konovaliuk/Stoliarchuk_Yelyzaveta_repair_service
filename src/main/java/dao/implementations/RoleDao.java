package dao.implementations;

import dao.interfaces.RoleInterface;
import entities.Role;
import jakarta.persistence.EntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RoleDao implements RoleInterface {
    private final EntityManager em;
    private static final Logger logger = LoggerFactory.getLogger(RoleDao.class);
    public RoleDao(EntityManager em){
        this.em = em;
    }

    @Override
    public Role findById(long roleId) {
        return em.find(Role.class, roleId);
    }

    @Override
    public Role findByName(String name){
        return em.createQuery("SELECT r FROM Role r WHERE r.name = :name", Role.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public List<Role> findAll() {
        return em.createQuery("SELECT r FROM Role r", Role.class).getResultList();
    }

    @Override
    public Role create(Role role) {
        em.persist(role);
        return role;
    }

    @Override
    public void delete(long roleId) {
        em.remove(em.find(Role.class, roleId));
    }
}
