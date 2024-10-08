package com.example.E_Commerce_Clean_Food_For_Life.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name); // This is correct if the 'Role' entity has a 'name' field
}
