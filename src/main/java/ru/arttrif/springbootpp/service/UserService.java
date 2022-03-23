package ru.arttrif.springbootpp.service;

import ru.arttrif.springbootpp.models.User;

import java.util.List;

public interface UserService {

    public List<User> getAllUsers();

    public void saveUser(User user);

    public User show (long id);

    public void update(User user);

    public void delete(User user);
}
