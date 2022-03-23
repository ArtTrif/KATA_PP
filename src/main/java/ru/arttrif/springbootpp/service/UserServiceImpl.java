package ru.arttrif.springbootpp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.arttrif.springbootpp.DAO.UserDAO;
import ru.arttrif.springbootpp.models.User;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    @Override
    public void saveUser(User user) {
        userDAO.save(user);
    }

    @Override
    public User show(long id) {
        return userDAO.getById(id);
    }

    @Override
    public void update(User user) {
        userDAO.save(user);
    }

    @Override
    public void delete(User user) {
        userDAO.delete(user);
    }
}
