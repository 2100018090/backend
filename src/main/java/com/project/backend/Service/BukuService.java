package com.project.backend.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.backend.Model.Buku;
import com.project.backend.Model.Kategori;
import com.project.backend.Repository.BukuRepository;
import com.project.backend.Repository.KategoriRepository;

@Service
public class BukuService {

    @Autowired
    private BukuRepository bukuRepository;

    @Autowired
    private KategoriRepository kategoriRepository;

    public List<Buku> getAllBuku() {
        return bukuRepository.findAll();
    }

    public Buku getBukuById(Long id) {
        return bukuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Buku dengan Id " + id + " tidak ditemukan"));
    }

    public Buku createBuku(Buku buku) {
        return bukuRepository.save(buku);
    }

    public Buku updateBuku(Long id, Buku bukuDetails) {
        Buku existingBuku = bukuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Buku dengan ID " + id + " tidak ditemukan"));

        existingBuku.setJudul(bukuDetails.getJudul());
        existingBuku.setIsbn(bukuDetails.getIsbn());
        existingBuku.setPenulis(bukuDetails.getPenulis());
        existingBuku.setPenerbit(bukuDetails.getPenerbit());
        existingBuku.setTahun(bukuDetails.getTahun());
        existingBuku.setAdmin(bukuDetails.getAdmin());
        existingBuku.setKategori(bukuDetails.getKategori());

        return bukuRepository.save(existingBuku);
    }

    public void deleteBuku(Long id) {
        Buku buku = bukuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Buku dengan ID " + id + " tidak ditemukan"));
        bukuRepository.delete(buku);
    }

    public List<Buku> searchByIsbn(String isbn) {
        return bukuRepository.findByIsbnContainingIgnoreCase(isbn);
    }

    public List<Buku> searchByJudul(String judul) {
        return bukuRepository.findByJudulContainingIgnoreCase(judul);
    }

    public List<Buku> searchByKategori(Long kategoriId) {
        Kategori kategori = kategoriRepository.findById(kategoriId)
                .orElseThrow(() -> new RuntimeException("Kategori tidak ditemukan"));
        return bukuRepository.findByKategori(kategori);
    }

}
