package ru.arttrif.springPP321.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.arttrif.springPP321.models.User;

import javax.management.Query;
import java.util.ArrayList;
import java.util.List;
@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        List<User> allUsers = session.createQuery("from User", User.class).getResultList();

        return allUsers;
    }

    @Override
    public void saveUser(User user) {
        sessionFactory.getCurrentSession().persist(user);
    }

    @Override
    public User show(long id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, id);
        return user;
    }

    @Override
    public void update(long id, User user) {
        User userUpdate = show(id);
        userUpdate.setName(user.getName());
        userUpdate.setSurname(user.getSurname());
    }

    @Override
    public void delete(long id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, id);
        session.delete(user);
    }
}
