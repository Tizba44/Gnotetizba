package com.restbnote.rest.services.impl;

import com.restbnote.rest.configs.exceptions.MyException;
import com.restbnote.rest.configs.exceptions.MyExceptionPayLoad;
import com.restbnote.rest.models.dto.EtudiantDto;
import com.restbnote.rest.models.entities.EtudiantEntity;
import com.restbnote.rest.repositories.EtudiantRepository;
import com.restbnote.rest.services.EtudiantService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.restbnote.rest.models.entities.ControleEntity;
import com.restbnote.rest.repositories.ControleRepository;

@Service
@AllArgsConstructor
public class EtudiantServiceImpl implements EtudiantService {

    private final EtudiantRepository etudiantRepository;
    private final ControleRepository controleRepository;


    @Override
    public double readMoyenneOfEtudiant(String mailEtudiantsID) {
        List<ControleEntity> controles = controleRepository.findAllByMailEtudiantsID(mailEtudiantsID);
        double total = 0;
        double totalCoef = 0;
        for (ControleEntity controle : controles) {
            total += controle.getNote() * controle.getCoef();
            totalCoef += controle.getCoef();
        }
        return total / totalCoef;
    }

    @Override
    public Map<String, Double> readMoyenneOfAllEtudiants() {
        List<EtudiantEntity> etudiants = etudiantRepository.findAll();
        Map<String, Double> moyennes = new HashMap<>();
        for (EtudiantEntity etudiant : etudiants) {
            double moyenne = readMoyenneOfEtudiant(etudiant.getMailID());
            moyennes.put(etudiant.getMailID(), moyenne);
        }
        return moyennes;
    }


    @Override
    public EtudiantDto createEtudiant(EtudiantDto etudiantDto) {
        EtudiantEntity etudiant = new EtudiantEntity();
        etudiant.setMailID(etudiantDto.getMailID());
        etudiant.setNom(etudiantDto.getNom());
        etudiant.setPrenom(etudiantDto.getPrenom());
        etudiant.setTelephone(etudiantDto.getTelephone());

        etudiant = etudiantRepository.save(etudiant);
        return EtudiantDto.builder()
                .id(etudiant.getId())
                .mailID(etudiant.getMailID())
                .nom(etudiant.getNom())
                .prenom(etudiant.getPrenom())
                .telephone(etudiant.getTelephone())
                .build();
    }

    @Override
    public List<EtudiantDto> readEtudiant() {
        List<EtudiantEntity> etudiants = etudiantRepository.findAll();
        List<EtudiantDto> etudiantDtos = new ArrayList<>();
        etudiants.stream()
                .map(p -> EtudiantDto.builder()
                        .id(p.getId())
                        .mailID(p.getMailID())
                        .nom(p.getNom())
                        .prenom(p.getPrenom())
                        .telephone(p.getTelephone())
                        .build()).forEach(etudiantDtos::add);
        return etudiantDtos;
    }


    @Override
    public EtudiantDto readOneEtudiant(String id) {
        EtudiantEntity etudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new MyException(
                        MyExceptionPayLoad.builder()
                                .httpCode(404)
                                .message("etudiant non trouvé")
                                .build()
                ));
        return EtudiantDto.builder()
                .id(etudiant.getId())
                .mailID(etudiant.getMailID())
                .nom(etudiant.getNom())
                .prenom(etudiant.getPrenom())
                .telephone(etudiant.getTelephone())
                .build();
    }


    @Override
    public EtudiantDto updateEtudiant(String id, EtudiantDto etudiantDto) {
        return etudiantRepository.findById(id)
                .map(existingEtudiant -> {
                    if (etudiantDto.getMailID() != null && !etudiantDto.getMailID().isEmpty()) {
                        existingEtudiant.setMailID(etudiantDto.getMailID());
                    }
                    if (etudiantDto.getNom() != null && !etudiantDto.getNom().isEmpty()) {
                        existingEtudiant.setNom(etudiantDto.getNom());
                    }
                    if (etudiantDto.getPrenom() != null && !etudiantDto.getPrenom().isEmpty()) {
                        existingEtudiant.setPrenom(etudiantDto.getPrenom());
                    }
                    if (etudiantDto.getTelephone() != null && !etudiantDto.getTelephone().isEmpty()) {
                        existingEtudiant.setTelephone(etudiantDto.getTelephone());
                    }


                    etudiantRepository.save(existingEtudiant);
                    etudiantDto.setId(existingEtudiant.getId());
                    return etudiantDto;
                })
                .orElseGet(() -> {
                    // If the etudiant doesn't exist, create a new one
                    EtudiantEntity newEtudiant = new EtudiantEntity();
                    newEtudiant.setMailID(etudiantDto.getMailID());
                    newEtudiant.setNom(etudiantDto.getNom());
                    newEtudiant.setPrenom(etudiantDto.getPrenom());
                    newEtudiant.setTelephone(etudiantDto.getTelephone());
                    newEtudiant = etudiantRepository.save(newEtudiant);
                    return EtudiantDto.builder()
                            .id(newEtudiant.getId())
                            .mailID(newEtudiant.getMailID())
                            .nom(newEtudiant.getNom())
                            .prenom(newEtudiant.getPrenom())
                            .telephone(newEtudiant.getTelephone())
                            .build();
                });
    }


    @Override
    public String deleteEtudiant(String id) {
        EtudiantEntity p = etudiantRepository.findById(id)
                .orElseThrow(() -> new MyException(
                        MyExceptionPayLoad.builder()
                                .httpCode(404)
                                .message("produit non trouvé")
                                .build()
                ));
        etudiantRepository.delete(p);
        return "etudiant supprimé";
    }


}
