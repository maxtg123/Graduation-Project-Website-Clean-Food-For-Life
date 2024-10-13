package com.example.E_Commerce_Clean_Food_For_Life.user;

import com.example.E_Commerce_Clean_Food_For_Life.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Đăng ký người dùng
    public void registerUser(User user) {
        // Kiểm tra xem người dùng đã tồn tại chưa
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email đã được sử dụng");
        }

        // Mã hóa mật khẩu
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(false); // Đặt active là false khi đăng ký
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        // Lưu người dùng
        userRepository.save(user);

        // Tạo OTP và gửi email
        tokenService.createAndSendOtp(user);
    }

    // Kích hoạt tài khoản bằng OTP
    public boolean activateUserAccount(Long userId, String otpCode) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (tokenService.verifyOtp(user.getId(), otpCode)) {
                // Nếu OTP hợp lệ thì kích hoạt người dùng
                user.setActive(true);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean passwordMatches(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}