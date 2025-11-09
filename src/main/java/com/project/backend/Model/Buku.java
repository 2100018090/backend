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

    @Column(nullable = false, length = 20)
    private String isbn;

    @Column(nullable = false, length = 100)
    private String penulis;

    @Column(nullable = false, length = 100)
    private String penerbit;

    @Column(nullable = false)
    private LocalDate tahun;

    public Buku() {
    }

    public Buku(Long id, Admin admin, Kategori kategori, String judul, String isbn, String penulis, String penerbit,
            LocalDate tahun) {
        this.id = id;
        this.admin = admin;
        this.kategori = kategori;
        this.judul = judul;
        this.isbn = isbn;
        this.penulis = penulis;
        this.penerbit = penerbit;
        this.tahun = tahun;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public LocalDate getTahun() {
        return tahun;
    }

    public void setTahun(LocalDate tahun) {
        this.tahun = tahun;
    }

}
