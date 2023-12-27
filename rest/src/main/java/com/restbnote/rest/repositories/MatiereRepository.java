package com.restbnote.rest.repositories;

import com.restbnote.rest.models.entities.EtudiantEntity;
import com.restbnote.rest.models.entities.MatiereEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MatiereRepository extends JpaRepository<MatiereEntity, String> {

    List<MatiereEntity> findAllByMailProfsID(String mailProfsID);


}
