package com.project.backend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.backend.Model.Buku;
import com.project.backend.Model.Kategori;

public interface BukuRepository extends JpaRepository<Buku, Long> {
    List<Buku> findByIsbnContainingIgnoreCase(String isbn);

    List<Buku> findByJudulContainingIgnoreCase(String judul);

    List<Buku> findByKategori(Kategori kategori);
}
