package ru.kata.spring.boot_security.demo.DAO;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class UserDaoImpl implements UserDAO {
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private final EntityManager entityManager;
    private RoleDAO roleDAO;

    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;

    }

    @Override
    public List<User> getAllUsers() {
        Session session = entityManager.unwrap(Session.class);
        List<User> allUsers = session.createQuery("from User", User.class).getResultList();

        return allUsers;
    }

    @Override
    public void saveUser(User user) {
        Session session = entityManager.unwrap(Session.class);
        session.persist(user);

    }

    @Override
    public User show(long id) {
        Session session = entityManager.unwrap(Session.class);
        return session.get(User.class, id);
    }

    @Override
    public void update(long id, User user) {
        User userUpdate = show(id);
        userUpdate.setFirstName(user.getFirstName());
        userUpdate.setLastName(user.getLastName());
        userUpdate.setAge(user.getAge());
        userUpdate.setEmail(user.getEmail());
        userUpdate.setPassword(user.getPassword());
        userUpdate.setAuthorities((List<Role>) user.getAuthorities());
    }

    @Override
    public void delete(long id) {
        Session session = entityManager.unwrap(Session.class);
        User user = session.get(User.class, id);
        session.delete(user);
    }

    public User findByEmail(String mail) {

        return entityManager.createQuery("select user from User user where user.mail=:mail", User.class)
                .setParameter("mail", mail).getSingleResult();
    }
}
