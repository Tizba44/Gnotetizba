package com.restbnote.rest.repositories;

import com.restbnote.rest.models.entities.ProfEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfRepository extends JpaRepository<ProfEntity, String> {
}
