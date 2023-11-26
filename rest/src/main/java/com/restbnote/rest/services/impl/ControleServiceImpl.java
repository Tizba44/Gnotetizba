package com.restbnote.rest.services.impl;

import com.restbnote.rest.configs.exceptions.MyException;
import com.restbnote.rest.configs.exceptions.MyExceptionPayLoad;
import com.restbnote.rest.models.dto.ControleDto;

import com.restbnote.rest.models.entities.ControleEntity;

import com.restbnote.rest.repositories.ControleRepository;

import com.restbnote.rest.services.ControleService;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class ControleServiceImpl implements ControleService {

    private final ControleRepository controleRepository;

    @Override
    public ControleDto createControle(ControleDto controleDto) {
        ControleEntity controle = new ControleEntity();
        controle.setDate(controleDto.getDate());
        controle.setNote(controleDto.getNote());
        controle.setCoef(controleDto.getCoef());
        controle.setMailID(controleDto.getMailID());
        controle.setAppreciation(controleDto.getAppreciation());
        controle.setIntitule(controleDto.getIntitule());
        controle.setNomMatiereID(controleDto.getNomMatiereID());
        controle = controleRepository.save(controle);
        return ControleDto.builder()
                .id(controle.getId())
                .date(controle.getDate())
                .note(controle.getNote())
                .coef(controle.getCoef())
                .mailID(controle.getMailID())
                .appreciation(controle.getAppreciation())
                .intitule(controle.getIntitule())
                .nomMatiereID(controle.getNomMatiereID())
                .build();
    }

    @Override
    public List<ControleDto> readControle() {
        List<ControleEntity> controles = controleRepository.findAll();
        List<ControleDto> controleDtos = new ArrayList<>();
        controles.stream()
                .map(c -> ControleDto.builder()
                        .id(c.getId())
                        .date(c.getDate())
                        .note(c.getNote())
                        .coef(c.getCoef())
                        .mailID(c.getMailID())
                        .appreciation(c.getAppreciation())
                        .intitule(c.getIntitule())
                        .nomMatiereID(c.getNomMatiereID())
                        .build()).forEach(controleDtos::add);
        return controleDtos;
    }

    @Override
    public ControleDto updateControle(String id, ControleDto controleDto) {
        return controleRepository.findById(id)
                .map(c -> {
                    c.setDate(controleDto.getDate());
                    c.setNote(controleDto.getNote());
                    c.setCoef(controleDto.getCoef());
                    c.setMailID(controleDto.getMailID());
                    c.setAppreciation(controleDto.getAppreciation());
                    c.setIntitule(controleDto.getIntitule());
                    c.setNomMatiereID(controleDto.getNomMatiereID());
                    controleRepository.save(c);
                    controleDto.setId(c.getId());
                    controleDto.setDate(c.getDate());
                    controleDto.setNote(c.getNote());
                    controleDto.setCoef(c.getCoef());
                    controleDto.setMailID(c.getMailID());
                    controleDto.setAppreciation(c.getAppreciation());
                    controleDto.setIntitule(c.getIntitule());
                    controleDto.setNomMatiereID(c.getNomMatiereID());
                    return controleDto;
                }).orElse(null);
    }

    @Override
    public String deleteControle(String id) {
        ControleEntity c = controleRepository.findById(id)
                .orElseThrow(() -> new MyException(
                        MyExceptionPayLoad.builder()
                                .httpCode(404)
                                .message("Controle non trouvé")
                                .build()
                ));
        controleRepository.delete(c);
        return "Controle supprimé !";
    }
}
