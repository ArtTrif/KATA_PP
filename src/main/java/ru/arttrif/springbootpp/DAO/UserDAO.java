package ru.arttrif.springbootpp.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.arttrif.springbootpp.models.User;

import java.util.List;


public interface UserDAO {
    public List<User> getAllUsers();

    public void saveUser(User user);

    public User show (long id);

    public void update(long id, User user);

    public void delete(long id);
}
