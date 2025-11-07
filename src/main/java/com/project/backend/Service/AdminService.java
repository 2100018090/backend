package com.project.backend.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.backend.Model.Admin;
import com.project.backend.Repository.AdminRepository;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class AdminService{

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
