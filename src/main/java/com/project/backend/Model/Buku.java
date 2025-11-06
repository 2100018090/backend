package com.project.backend.Model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "buku")
public class Buku {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_admin", nullable = false)
    private Admin admin;

    @ManyToOne
    @JoinColumn(name = "id_kategori", nullable = false)
    private Kategori kategori;

    @Column(nullable = false, length = 100)
    private String judul;

    @Column (nullable = false, length = 100)
    private String penulis;

    @Column (nullable = false, length = 100)
    private String penerbit;

    @Column (nullable = false)
    private LocalDate tahun;
}
