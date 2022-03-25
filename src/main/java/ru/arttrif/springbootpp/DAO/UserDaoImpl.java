package ru.arttrif.springbootpp.DAO;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.arttrif.springbootpp.models.User;

import javax.persistence.EntityManager;
import java.util.List;
@Repository
public class UserDaoImpl implements UserDAO {
    private final EntityManager entityManager;

    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<User> getAllUsers() {
        Session session = entityManager.unwrap(Session.class);
        List<User> allUsers = session.createQuery("from User", User.class).getResultList();

        return allUsers;
    }

    @Override
    public void saveUser(User user) {
        Session session = entityManager.unwrap(Session.class);
        session.persist(user);
    }

    @Override
    public User show(long id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(User.class, id);
    }

    @Override
    public void update(long id, User user) {
        User userUpdate = show(id);
        userUpdate.setName(user.getName());
        userUpdate.setSurname(user.getSurname());
    }

    @Override
    public void delete(long id) {
        Session session = entityManager.unwrap(Session.class);
        User user = session.get(User.class, id);
        session.delete(user);
    }
}
