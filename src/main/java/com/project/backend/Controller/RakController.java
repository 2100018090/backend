package com.project.backend.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.backend.Model.Rak;
import com.project.backend.Service.RakService;

@RestController
@RequestMapping("/api/rak")
@CrossOrigin(origins = "*")
public class RakController {
    @Autowired
    private RakService rakService;

    @GetMapping
    public ResponseEntity<List<Rak>> getAllRak() {
        return ResponseEntity.ok(rakService.getAllRak());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rak> getRakById(@PathVariable Long id) {
        return ResponseEntity.ok(rakService.getRakById(id));
    }

    @PostMapping
    public ResponseEntity<Rak> createRak(@RequestBody Rak rak) {
        return ResponseEntity.ok(rakService.createRak(rak));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rak> updateRak(@PathVariable Long id, @RequestBody Rak rak) {
        return ResponseEntity.ok(rakService.updateRak(id, rak));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRak(@PathVariable Long id) {
        rakService.deleteRak(id);
        return ResponseEntity.ok("Rak dengan ID " + id + " berhasil dihapus");
    }
}