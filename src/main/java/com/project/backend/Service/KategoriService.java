package com.project.backend.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.backend.Model.Kategori;
import com.project.backend.Model.Rak;
import com.project.backend.Repository.KategoriRepository;
import com.project.backend.Repository.RakRepository;

@Service
public class KategoriService {
    @Autowired
    private KategoriRepository kategoriRepository;

    @Autowired 
    private RakRepository rakRepository;

    public List<Kategori> getAllKategori() {
        return kategoriRepository.findAll();
    }

    public Kategori getKategoriById(Long id) {
        return kategoriRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kategori dengan ID " + id + " tidak ditemukan"));
    }

    public Kategori createKategori(Long rakId, Kategori kategori) {
        Rak rak = rakRepository.findById(rakId)
                .orElseThrow(() -> new RuntimeException("Rak dengan ID " + rakId + " tidak ditemukan"));
        kategori.setRak(rak);
        return kategoriRepository.save(kategori);
    }

    public Kategori updateKategori(Long id, Kategori kategoriBaru) {
        Kategori existingKategori = kategoriRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kategori dengan ID " + id + " tidak ditemukan"));
        existingKategori.setNama(kategoriBaru.getNama());
        existingKategori.setRak(kategoriBaru.getRak());
        return kategoriRepository.save(existingKategori);
    }

    public void deleteKategori(Long id) {
        Kategori kategori = kategoriRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kategori dengan ID " + id + " tidak ditemukan"));
        kategoriRepository.delete(kategori);
    }
}
