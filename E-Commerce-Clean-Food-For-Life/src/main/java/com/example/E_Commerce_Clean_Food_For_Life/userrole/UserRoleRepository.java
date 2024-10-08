package com.example.E_Commerce_Clean_Food_For_Life.userrole;

import com.example.E_Commerce_Clean_Food_For_Life.userroleid.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {

    // Tìm tất cả các vai trò của một người dùng cụ thể
    List<UserRole> findByUser_Id(Long userId);

    // Tìm tất cả người dùng có một vai trò cụ thể
    List<UserRole> findByRole_Id(Long roleId);
}