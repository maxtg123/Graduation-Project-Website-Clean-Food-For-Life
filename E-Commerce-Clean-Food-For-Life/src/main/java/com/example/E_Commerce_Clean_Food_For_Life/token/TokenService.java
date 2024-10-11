package com.example.E_Commerce_Clean_Food_For_Life.token;

import com.example.E_Commerce_Clean_Food_For_Life.Email.EmailService;
import com.example.E_Commerce_Clean_Food_For_Life.user.TokenRepository;
import com.example.E_Commerce_Clean_Food_For_Life.user.User;
import com.example.E_Commerce_Clean_Food_For_Life.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private EmailService emailService;

    // Tạo và gửi mã OTP qua email
    public void createAndSendOtp(User user) {
        String otpCode = generateOtp();
        Token token = new Token();
        token.setUserId(user.getId());
        token.setOtpCode(otpCode);
        token.setCreatedAt(LocalDateTime.now());
        token.setExpiresAt(LocalDateTime.now().plusMinutes(10)); // OTP có hiệu lực trong 10 phút
        token.setUsed(false);

        tokenRepository.save(token);

        // Gửi email OTP
        emailService.sendOtpEmail(user.getEmail(),  otpCode, user.getUsername());
    }

    // Xác minh OTP
    public boolean verifyOtp(Long userId, String otpCode) {
        Optional<Token> optionalToken = tokenRepository.findByUserId(userId);
        if (optionalToken.isPresent()) {
            Token token = optionalToken.get();
            if (token.getOtpCode().equals(otpCode) && !token.isUsed() && token.getExpiresAt().isAfter(LocalDateTime.now())) {
                // Đánh dấu token là đã sử dụng
                token.setUsed(true);
                tokenRepository.save(token);
                return true;
            }
        }
        return false;
    }

    private String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }
}