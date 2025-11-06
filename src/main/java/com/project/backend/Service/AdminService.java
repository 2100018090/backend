package com.project.backend.Service;

import java.util.List;

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
}
