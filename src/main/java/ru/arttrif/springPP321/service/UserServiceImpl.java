package ru.arttrif.springPP321.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.arttrif.springPP321.dao.UserDAO;
import ru.arttrif.springPP321.models.User;

import java.util.List;
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDAO userDAO;
    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userDAO.saveUser(user);
    }

    @Override
    @Transactional
    public User show(long id) {
        return userDAO.show(id);
    }
    @Override
    @Transactional
    public void update(long id, User user){
        userDAO.update(id, user);
    }

    @Override
    @Transactional
    public void delete(long id) {
        userDAO.delete(id);
    }
}
