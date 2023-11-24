package com.restbnote.rest.repositories;

import com.restbnote.rest.models.entities.MatiereEntity;
import org.springframework.data.jpa.repository.JpaRepository;

//Matiere
public interface MatiereRepository extends JpaRepository<MatiereEntity, String> {
}
