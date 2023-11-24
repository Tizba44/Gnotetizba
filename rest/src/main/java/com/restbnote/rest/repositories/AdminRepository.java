
package com.restbnote.rest.repositories;

import com.restbnote.rest.models.entities.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminEntity, String> {
}



