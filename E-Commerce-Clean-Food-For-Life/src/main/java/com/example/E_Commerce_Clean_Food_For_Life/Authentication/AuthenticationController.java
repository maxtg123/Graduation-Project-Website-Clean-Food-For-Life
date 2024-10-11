package com.example.E_Commerce_Clean_Food_For_Life.Authentication;


import com.example.E_Commerce_Clean_Food_For_Life.user.User;
import com.example.E_Commerce_Clean_Food_For_Life.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody User user) {
        try {
            userService.registerUser(user);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "User registered successfully. Please check your email for OTP.");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage()); // Lỗi từ việc đăng ký
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    // Kích hoạt tài khoản bằng OTP
    @GetMapping("/activate")
    public ResponseEntity<String> activateAccount(@RequestParam Long userId, @RequestParam String otpCode) {
        boolean activated = userService.activateUserAccount(userId, otpCode);
        if (activated) {
            return ResponseEntity.ok("Account activated successfully.");
        } else {
            return ResponseEntity.badRequest().body("Invalid OTP or expired.");
        }
    }

//    @PostMapping("/login")
//    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest) {
//        Map<String, Object> response = new HashMap<>();
//
//        String email = loginRequest.getEmail();
//        String password = loginRequest.getPassword();
//
//        // Tìm người dùng theo email
//        Optional<User> optionalUser = userService.findByEmail(email);
//
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//            // Kiểm tra mật khẩu
//            if (userService.passwordMatches(password, user.getPassword())) { // Giả sử bạn có phương thức passwordMatches
//                response.put("success", true);
//                response.put("message", "Login successful");
//                // Có thể tạo JWT ở đây nếu cần
//                return ResponseEntity.ok(response);
//            } else {
//                response.put("success", false);
//                response.put("message", "Invalid password");
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
//            }
//        } else {
//            response.put("success", false);
//            response.put("message", "User not found");
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
//        }
//    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest) {
        Map<String, Object> response = new HashMap<>();

        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        // Tìm người dùng theo email
        Optional<User> optionalUser = userService.findByEmail(email);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // Kiểm tra mật khẩu
            if (userService.passwordMatches(password, user.getPassword())) {
                response.put("success", true);
                response.put("message", "Login successful");
                // Không cần tạo token
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "Invalid password");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        } else {
            response.put("success", false);
            response.put("message", "User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

}