package com.project.backend.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.backend.Model.Admin;
import com.project.backend.Repository.AdminRepository;

@Service
public class AdminService {
    
    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAllAdmin(){
        return adminRepository.findAll();
    }

    public Optional<Admin> getAdminById(Long id){
        return adminRepository.findById(id);
    }

    public Admin createAdmin(Admin admin){
        if(adminRepository.existsByUsername(admin.getUsername())){
            throw new RuntimeException("Username sudah digunakan");
        }
        return adminRepository.save(admin);
    }

    public Admin updateAdmin(Long id, Admin adminDetails){
        Admin admin = adminRepository.findById(id).orElseThrow(() -> new RuntimeException("Admin tidak ditemukan"));

        if (!admin.getUsername().equals(adminDetails.getUsername()) && adminRepository.existsByUsername(adminDetails.getUsername())) {
            throw new RuntimeException("Username sudah digunakan oleh admin lain");
        }

        admin.setUsername(adminDetails.getUsername());
        admin.setPassword(adminDetails.getPassword());
        admin.setNama(adminDetails.getNama());

        return adminRepository.save(admin);
    }

    public void deleteAdmin(Long id) {
        if (!adminRepository.existsById(id)) {
            throw new RuntimeException("Admin tidak ditemukan");
        }
        adminRepository.deleteById(id);
    }
}
