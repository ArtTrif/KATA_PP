package ru.kata.spring.boot_security.demo.DAO;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO{
    private final EntityManager entityManager;

    @Autowired
    public RoleDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public List<Role> getAllRole() {
        Session session = entityManager.unwrap(Session.class);
        List<Role> allRoles = session.createQuery("from Role", Role.class).getResultList();
        return allRoles;
    }

    @Override
    public Role roleById(long user_id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(Role.class, user_id);
    }
}
