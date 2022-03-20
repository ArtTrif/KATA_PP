package ru.arttrif.springPP321.service;

import ru.arttrif.springPP321.models.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();

    public void saveUser(User user);

    public User show (long id);

    public void update(long id, User user);

    public void delete(long id);
}
