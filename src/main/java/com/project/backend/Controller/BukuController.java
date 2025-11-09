package com.project.backend.Controller;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.backend.Model.Buku;
import com.project.backend.Service.BukuService;

@RestController
@RequestMapping("/api/buku")
@CrossOrigin(origins = "*")
public class BukuController {

    @Autowired
    private BukuService bukuService;

    @GetMapping
    public ResponseEntity<List<Buku>> getAllBuku() {
        return ResponseEntity.ok(bukuService.getAllBuku());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Buku> getBukuById(@PathVariable Long id) {
        return ResponseEntity.ok(bukuService.getBukuById(id));
    }

    @PostMapping
    public ResponseEntity<Buku> createBuku(@RequestBody Buku buku) {
        return ResponseEntity.ok(bukuService.createBuku(buku));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Buku> updateBuku(@PathVariable Long id, @RequestBody Buku buku) {
        return ResponseEntity.ok(bukuService.updateBuku(id, buku));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBuku(@PathVariable Long id) {
        bukuService.deleteBuku(id);
        return ResponseEntity.ok("Buku dengan ID " + id + " berhasil dihapus");
    }

    @PostMapping("/search/judul")
    public List<Buku> searchByJudul(@RequestBody Map<String, String> body) {
        String judul = body.get("judul");
        return bukuService.searchByJudul(judul);
    }

    @PostMapping("/search/isbn")
    public List<Buku> searchByIsbn(@RequestBody Map<String, String> body) {
        String isbn = body.get("isbn");
        return bukuService.searchByIsbn(isbn);
    }

    @GetMapping("/search/kategori/{id}")
    public List<Buku> searchByKategori(@PathVariable Long id) {
        return bukuService.searchByKategori(id);
    }
}
