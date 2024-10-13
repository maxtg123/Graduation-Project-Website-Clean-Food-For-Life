package com.example.E_Commerce_Clean_Food_For_Life.user;


import com.example.E_Commerce_Clean_Food_For_Life.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

}