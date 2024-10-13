package com.example.E_Commerce_Clean_Food_For_Life.userrole;

import com.example.E_Commerce_Clean_Food_For_Life.role.Role;
import com.example.E_Commerce_Clean_Food_For_Life.user.User;
import com.example.E_Commerce_Clean_Food_For_Life.userroleid.UserRoleId;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "user_roles")
@IdClass(UserRoleId.class)
public class UserRole implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    // Constructors
    public UserRole() {}

    public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
    }

    // Getters and Setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}