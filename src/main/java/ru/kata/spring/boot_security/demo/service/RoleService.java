package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;

public interface RoleService {
    public List<Role> getAllRole();

    public Role roleById (long user_id);

    public Role roleByName (String roleName);
}
