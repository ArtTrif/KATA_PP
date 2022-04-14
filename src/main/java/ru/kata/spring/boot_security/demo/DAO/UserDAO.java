package ru.kata.spring.boot_security.demo.DAO;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;


public interface UserDAO {
    public List<User> getAllUsers();

    public void saveUser(User user);

    public User show (long id);

    public void update(User user);

    public void delete(long id);

    public User findByEmail(String mail);
}
