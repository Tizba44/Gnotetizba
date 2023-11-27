package com.restbnote.rest.services;

import com.restbnote.rest.models.dto.EtudiantDto;

import java.util.List;




public interface EtudiantService {
    EtudiantDto createEtudiant(final EtudiantDto etudiantDto);
    List<EtudiantDto> readEtudiant();
    EtudiantDto updateEtudiant(final String id, final EtudiantDto  etudiantDto);
    String deleteEtudiant(String id);

}
