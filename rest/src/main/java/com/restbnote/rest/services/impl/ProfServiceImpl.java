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
    public ProfDto updateProf(String id, ProfDto profDto) {
        return profRepository.findById(id)
                .map(p-> {
                    p.setMailID(profDto.getMailID());
                    p.setNom(profDto.getNom());
                    p.setPrenom(profDto.getPrenom());
                    p.setTelephone(profDto.getTelephone());
                    p.setMotDePasse(profDto.getMotDePasse());
                    profRepository.save(p);
                    profDto.setId(p.getId());
                    return profDto;
                }).orElse(null);
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
