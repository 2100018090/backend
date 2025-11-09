package com.project.backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.backend.Model.Kategori;
import com.project.backend.Service.KategoriService;

@RestController
@RequestMapping("/api/kategori")
@CrossOrigin(origins = "*")
public class KategoriController {

    @Autowired
    private KategoriService kategoriService;

    @GetMapping
    public List<Kategori> getAllKategori() {
        return kategoriService.getAllKategori();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Kategori> getkategoriById(@PathVariable Long id) {
        return ResponseEntity.ok(kategoriService.getKategoriById(id));
    }

    @PostMapping("/{rakId}")
    public ResponseEntity<Kategori> crateKategori(@PathVariable Long rakId, @RequestBody Kategori kategori) {
        return ResponseEntity.ok(kategoriService.createKategori(rakId, kategori));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Kategori> updateKategori(@PathVariable Long id, @RequestBody Kategori kategori) {
        return ResponseEntity.ok(kategoriService.updateKategori(id, kategori));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteKategori(@PathVariable Long id) {
        kategoriService.deleteKategori(id);
        return ResponseEntity.ok("Kategori dengan ID " + id + " berhasil dihapus");
    }
}
