package com.example.E_Commerce_Clean_Food_For_Life.user;

import com.example.E_Commerce_Clean_Food_For_Life.token.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByUserId(Long userId); // Tìm kiếm token theo userId
   Optional<Token> findByOtpCode(String otpCode); // Tìm kiếm token theo otpCode
}
