package com.project.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Jika kamu punya filter JWT, bisa diinject di sini (optional)
    // private final JwtAuthenticationFilter jwtAuthFilter;

    // public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter) {
    //     this.jwtAuthFilter = jwtAuthFilter;
    // }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Nonaktifkan CSRF (biasanya untuk REST API)
            .csrf(csrf -> csrf.disable())

            // Tentukan endpoint yang bebas diakses dan yang butuh auth
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/api/admin/**", "/api/rak/**"
                ).permitAll()

                // Sisanya butuh login
                .anyRequest().authenticated()
            )

            // Stateless karena REST API (tidak pakai session)
            .sessionManagement(sess -> sess
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );

        // Jika kamu pakai JWT filter, aktifkan ini
        // http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // Encoder untuk password
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Authentication manager (dibutuhkan untuk login)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}