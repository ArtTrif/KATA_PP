package ru.arttrif.springPP321.dao;

import ru.arttrif.springPP321.models.User;

import java.util.List;

public interface UserDAO {
    public List<User> getAllUsers();

    public void saveUser(User user);

    public User show (long id);

    public void update(long id, User user);

    public void delete(long id);
}
