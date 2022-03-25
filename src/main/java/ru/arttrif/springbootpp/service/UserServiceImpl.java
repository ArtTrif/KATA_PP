package ru.arttrif.springbootpp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        return userDAO.getAllUsers();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userDAO.saveUser(user);
    }

    @Override
    public User show(long id) {
        return userDAO.show(id);
    }

    @Override
    @Transactional
    public void update(long id, User user) {
        userDAO.update(id, user);
    }

    @Override
    @Transactional
    public void delete(long id) {
        userDAO.delete(id);
    }
}
