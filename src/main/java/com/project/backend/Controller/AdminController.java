package com.project.backend.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.backend.Model.Admin;
import com.project.backend.Service.AdminService;
import com.project.backend.Util.JwtUtil;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping
    public List<Admin> getAllAdmin() {
        return adminService.getAllAdmin();
    }

    @GetMapping("/{id}")
    public Admin getAdminById(@PathVariable Long id) {
        return adminService.getAdminById(id).orElseThrow(() -> new RuntimeException("Admin tidak ditemukan"));
    }

    @PostMapping
    public Admin createAdmin(@RequestBody Admin admin) {
        return adminService.registerAdmin(admin);
    }

    @PutMapping("/{id}")
    public Admin updateAdmin(@PathVariable Long id, @RequestBody Admin adminDetails) {
        return adminService.updateAdmin(id, adminDetails);
    }

    @DeleteMapping("/{id}")
    public String deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return "Admin dengan ID " + id + " berhasil dihapus";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Admin admin) {
        try {
            Map<String, String> tokenJson = adminService.loginAdmin(admin.getUsername(), admin.getPassword());
            return ResponseEntity.ok(tokenJson);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            Map<String, String> response = adminService.logoutAdmin(token);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.ok(Map.of("message", "No token provided"));
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(@RequestHeader("Authorization") String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            if (!adminService.isTokenValid(token)) {
                return ResponseEntity.status(401).body(Map.of("message", "Token sudah logout"));
            }

            String username = jwtUtil.extractUsername(token);
            Map<String, String> response = new HashMap<>();
            response.put("username", username);
            response.put("message", "Token valid");
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(401).body(Map.of("message", "Invalid or missing token"));
    }

}