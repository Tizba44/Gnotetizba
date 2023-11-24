package com.restbnote.rest.repositories;

import com.restbnote.rest.models.entities.EtudiantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<EtudiantEntity, String> {
}
