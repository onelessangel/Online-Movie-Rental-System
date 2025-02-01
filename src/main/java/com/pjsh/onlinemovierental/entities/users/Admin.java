package com.pjsh.onlinemovierental.entities.users;

import jakarta.persistence.Entity;

@Entity
public class Admin extends AbstractUser{
    private String role;

    public Admin() {
        super();
    }

    public Admin(Long id, String name, String email, String password, String role) {
        super(name, email, password);
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
