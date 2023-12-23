package com.restbnote.rest.services;

import com.restbnote.rest.models.dto.EtudiantDto;
import com.restbnote.rest.models.entities.EtudiantEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;






public interface EtudiantService {
    EtudiantDto createEtudiant(final EtudiantDto etudiantDto);
    List<EtudiantDto> readEtudiant();

    EtudiantDto readOneEtudiant(final String id);
    EtudiantDto updateEtudiant(final String id, final EtudiantDto  etudiantDto);
    String deleteEtudiant(String id);

    double readMoyenneOfEtudiant(String mailEtudiantsID);
    Map<String, Double> readMoyenneOfAllEtudiants();

    Map<String, Double> findBestAndWorstAverageOfAllEtudiants();
}
