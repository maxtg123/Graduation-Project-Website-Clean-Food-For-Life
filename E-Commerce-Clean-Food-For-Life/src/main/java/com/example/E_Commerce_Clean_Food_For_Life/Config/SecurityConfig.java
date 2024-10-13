package com.example.E_Commerce_Clean_Food_For_Life.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/register", "/api/auth/activate", "/api/auth/login").permitAll() // Cho phép mọi người
                        .requestMatchers("/api/admin/**").hasRole("ADMIN") // Chỉ cho admin
                        .anyRequest().hasRole("USER") // Người dùng thông thường
                )
                .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login?logout"));

        return http.build();
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/auth/register", "/api/auth/activate", "/api/auth/login").permitAll() // Công khai cho mọi người
//                        .requestMatchers("/api/admin/**").hasRole("ADMIN") // Chỉ cho admin
//                        .anyRequest().hasRole("USER") // Người dùng thông thường
//                )
//                .cors(cors -> cors.configurationSource(request -> {
//                    var source = new org.springframework.web.cors.CorsConfiguration();
//                    source.setAllowedOrigins(List.of("http://localhost:4200")); // Địa chỉ frontend
//                    source.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
//                    source.setAllowedHeaders(List.of("*"));
//                    source.setAllowCredentials(true);
//                    return source;
//                }));
//
//        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
