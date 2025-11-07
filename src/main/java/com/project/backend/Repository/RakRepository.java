package com.project.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.backend.Model.Rak;

@Repository
public interface RakRepository extends JpaRepository<Rak, Long>{
    boolean existsByCode(String Code);
}
