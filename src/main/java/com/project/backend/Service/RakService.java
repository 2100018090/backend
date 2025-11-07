package com.project.backend.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.backend.Model.Rak;
import com.project.backend.Repository.RakRepository;

@Service
public class RakService {
    @Autowired
    private RakRepository rakRepository;

    public List<Rak> getAllRak(){
        return rakRepository.findAll();
    }

    public Rak getRakById(Long id){
        return rakRepository.findById(id).orElseThrow(() -> new RuntimeException("Rak dengan ID " + id + " tidak ditemukan"));
    }

    public Rak createRak(Rak rak){
        if(rakRepository.existsByCode(rak.getCode())){
            throw new RuntimeException("Kode Rak sudah digunakan!");
        }
        return rakRepository.save(rak);
    }

    public Rak updateRak(Long id, Rak rak){
        Rak existingRak = rakRepository.findById(id).orElseThrow(() -> new RuntimeException("Rak dengan ID " + id + " tidak ditemukan"));
        existingRak.setCode(rak.getCode());
        existingRak.setNama(rak.getNama());
        return rakRepository.save(existingRak);
    }

    public void deleteRak(Long id){
        Rak rak = rakRepository.findById(id).orElseThrow(() -> new RuntimeException("Rak dengan ID " + id + " tidak ditemukan"));
        rakRepository.delete(rak);
    }
}