package com.restbnote.rest.services.impl;

import com.restbnote.rest.configs.exceptions.MyException;
import com.restbnote.rest.configs.exceptions.MyExceptionPayLoad;
import com.restbnote.rest.models.dto.ProfDto;
import com.restbnote.rest.models.entities.ProfEntity;
import com.restbnote.rest.repositories.ProfRepository;
import com.restbnote.rest.services.ProfService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProfServiceImpl implements ProfService {

    private final ProfRepository profRepository;
    @Override
    public List<ProfDto> readProf() {
        List<ProfEntity> profs = profRepository.findAll();
        List<ProfDto> profDtos = new ArrayList<>();
        profs.stream()
                .map(p -> ProfDto.builder()
                        .id(p.getId())
                        .mailID(p.getMailID())
                        .nom(p.getNom())
                        .prenom(p.getPrenom())
                        .telephone(p.getTelephone())
                        .motDePasse(p.getMotDePasse())
                        .build()).forEach(profDtos::add);
        return profDtos;
    }
    @Override
    public ProfDto readOneProf(String id) {
        ProfEntity prof = profRepository.findById(id)
                .orElseThrow(() -> new MyException(
                        MyExceptionPayLoad.builder()
                                .httpCode(404)
                                .message("prof non trouvé")
                                .build()
                ));
        return ProfDto.builder()
                .id(prof.getId())
                .mailID(prof.getMailID())
                .nom(prof.getNom())
                .prenom(prof.getPrenom())
                .telephone(prof.getTelephone())
                .motDePasse(prof.getMotDePasse())
                .build();
    }


    @Override
    public ProfDto createProf(ProfDto profDto) {
        ProfEntity prof = new ProfEntity();
        prof.setMailID(profDto.getMailID());
        prof.setNom(profDto.getNom());
        prof.setPrenom(profDto.getPrenom());
        prof.setTelephone(profDto.getTelephone());
        prof.setMotDePasse(profDto.getMotDePasse());
        prof = profRepository.save(prof);
        return ProfDto.builder()
                .id(prof.getId())
                .mailID(prof.getMailID())
                .nom(prof.getNom())
                .prenom(prof.getPrenom())
                .telephone(prof.getTelephone())
                .motDePasse(prof.getMotDePasse())
                .build();
    }

    @Override
    public ProfDto updateProf(String id, ProfDto profDto) {
        return profRepository.findById(id)
                .map(existingProf -> {
                    if (profDto.getMailID() != null && !profDto.getMailID().isEmpty()) {
                        existingProf.setMailID(profDto.getMailID());
                    }
                    if (profDto.getNom() != null && !profDto.getNom().isEmpty()) {
                        existingProf.setNom(profDto.getNom());
                    }
                    if (profDto.getPrenom() != null && !profDto.getPrenom().isEmpty()) {
                        existingProf.setPrenom(profDto.getPrenom());
                    }
                    if (profDto.getTelephone() != null && !profDto.getTelephone().isEmpty()) {
                        existingProf.setTelephone(profDto.getTelephone());
                    }
                    if (profDto.getMotDePasse() != null && !profDto.getMotDePasse().isEmpty()) {
                        existingProf.setMotDePasse(profDto.getMotDePasse());
                    }
                    // ... update other fields similarly

                    profRepository.save(existingProf);
                    profDto.setId(existingProf.getId());
                    return profDto;
                })
                .orElseGet(() -> {
                    // If the prof doesn't exist, create a new one
                    ProfEntity newProf = new ProfEntity();
                    newProf.setMailID(profDto.getMailID());
                    newProf.setNom(profDto.getNom());
                    newProf.setPrenom(profDto.getPrenom());
                    newProf.setTelephone(profDto.getTelephone());
                    newProf.setMotDePasse(profDto.getMotDePasse());
                    newProf = profRepository.save(newProf);
                    return ProfDto.builder()
                            .id(newProf.getId())
                            .mailID(newProf.getMailID())
                            .nom(newProf.getNom())
                            .prenom(newProf.getPrenom())
                            .telephone(newProf.getTelephone())
                            .motDePasse(newProf.getMotDePasse())
                            .build();
                });
    }


    @Override
    public String deleteProf(String id) {
        ProfEntity p = profRepository.findById(id)
                .orElseThrow(() -> new MyException(
                        MyExceptionPayLoad.builder()
                                .httpCode(404)
                                .message("produit non trouvé")
                                .build()
                ));
        profRepository.delete(p);
        return "prof supprimé";
    }

}
