package com.example.E_Commerce_Clean_Food_For_Life.Authentication;

import com.example.E_Commerce_Clean_Food_For_Life.user.User;
import com.example.E_Commerce_Clean_Food_For_Life.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

    @Autowired
    private UserService userService;
//
//    // Đăng ký người dùng
//    @PostMapping("/register")
//    public ResponseEntity<String> register(@RequestBody User user) {
//        userService.registerUser(user);
//        return ResponseEntity.ok("User registered successfully. Please check your email for OTP.");
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<String> register(@RequestBody User user) {
//        try {
//            userService.registerUser(user);
//            return ResponseEntity.ok("User registered successfully. Please check your email for OTP.");
//        } catch (RuntimeException e) {
//            return ResponseEntity.badRequest().body(e.getMessage()); // Trả về thông báo lỗi cụ thể
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đăng ký không thành công. Vui lòng thử lại.");
//        }
//    }

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

    // Đăng nhập (có thể dùng JWT hoặc session)
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        // Viết logic kiểm tra email và password tại đây
        // Trả về token JWT hoặc thông báo thành công
        return ResponseEntity.ok("Login successful");
    }
}
