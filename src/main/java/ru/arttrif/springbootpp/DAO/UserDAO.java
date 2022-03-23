package ru.arttrif.springbootpp.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.arttrif.springbootpp.models.User;
@Repository
public interface UserDAO extends JpaRepository <User, Long> {
}
