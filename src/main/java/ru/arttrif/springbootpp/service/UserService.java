package ru.arttrif.springbootpp.service;

import ru.arttrif.springbootpp.models.User;

import java.util.List;

public interface UserService {

    public List<User> getAllUsers();

    public void saveUser(User user);

    public User show (long id);

    public void update(long id, User user);

    public void delete(long id);
}
