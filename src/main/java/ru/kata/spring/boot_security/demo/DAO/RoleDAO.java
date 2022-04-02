package ru.kata.spring.boot_security.demo.DAO;


import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;

public interface RoleDAO {
    public List<Role> getAllRole();

    public Role roleById (long user_id);
}
