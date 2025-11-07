package com.project.backend.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.backend.Model.Admin;
import com.project.backend.Repository.AdminRepository;
import com.project.backend.Util.JwtUtil;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // Simpan token yang logout
    private Set<String> tokenBlacklist = new HashSet<>();

    // Login admin → kembalikan JSON token
    public Map<String, String> loginAdmin(String username, String password) throws Exception {
        Optional<Admin> userOpt = adminRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            Admin user = userOpt.get();
            if (encoder.matches(password, user.getPassword())) {
                String token = jwtUtil.generateToken(user.getUsername());
                Map<String, String> response = new HashMap<>();
                response.put("token", token);
                response.put("type", "Bearer");
                return response;
            }
        }
        throw new Exception("Invalid username or password");
    }

    // Logout admin → tambahkan token ke blacklist
    public Map<String, String> logoutAdmin(String token) {
        tokenBlacklist.add(token); // tambahkan token ke blacklist
        Map<String, String> response = new HashMap<>();
        response.put("message", "Logged out successfully");
        response.put("token", token);
        return response;
    }

    // Cek apakah token valid dan tidak di blacklist
    public boolean isTokenValid(String token) {
        if (tokenBlacklist.contains(token)) {
            return false; // token sudah logout
        }
        return jwtUtil.validateToken(token); // validasi JWT normal
    }

    public List<Admin> getAllAdmin() {
        return adminRepository.findAll();
    }

    public Optional<Admin> getAdminById(Long id) {
        return adminRepository.findById(id);
    }

    public Admin registerAdmin(Admin admin) {
        // Hash password sebelum disimpan
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }

    public Admin updateAdmin(Long id, Admin updatedAdmin) {
        Optional<Admin> optionalAdmin = adminRepository.findById(id);

        if (optionalAdmin.isPresent()) {
            Admin existingAdmin = optionalAdmin.get();

            existingAdmin.setUsername(updatedAdmin.getUsername());
            existingAdmin.setNama(updatedAdmin.getNama());

            // Jika password baru dikirim dan tidak kosong → hash ulang
            if (updatedAdmin.getPassword() != null && !updatedAdmin.getPassword().isBlank()) {
                existingAdmin.setPassword(passwordEncoder.encode(updatedAdmin.getPassword()));
            }

            return adminRepository.save(existingAdmin);
        } else {
            throw new RuntimeException("Admin dengan ID " + id + " tidak ditemukan");
        }
    }

    public void deleteAdmin(Long id) {
        if (!adminRepository.existsById(id)) {
            throw new RuntimeException("Admin tidak ditemukan");
        }
        adminRepository.deleteById(id);
    }
}
