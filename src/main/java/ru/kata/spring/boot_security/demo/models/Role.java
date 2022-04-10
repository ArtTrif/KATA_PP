package ru.kata.spring.boot_security.demo.models;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "role_table")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name_role")
    private String nameRole;


    /*@ManyToMany()
    private List<User> user;

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }*/

    public Role() {
    }

    public Role(String nameRole) {
        this.nameRole = nameRole;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }

    @Override
    public String getAuthority() {
        return getNameRole();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return id == role.id && nameRole.equals(role.nameRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameRole);
    }

    @Override
    public String toString() {
        return (nameRole.equals("ROLE_ADMIN")) ? "ADMIN" : (nameRole.equals("ROLE_USER")) ? "USER" : "Роль неопределенна";
    }
}

