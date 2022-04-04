package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.DAO.RoleDAO;
import ru.kata.spring.boot_security.demo.DAO.UserDAO;
import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDAO roleDAO;
    @Autowired
    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public List<Role> getAllRole() {
        return roleDAO.getAllRole();
    }

    @Override
    public Role roleById(long role_id) {
        return roleDAO.roleById(role_id);
    }

    @Override
    public Role roleByName(String roleName) {
        return roleDAO.roleByName(roleName);
    }
}
