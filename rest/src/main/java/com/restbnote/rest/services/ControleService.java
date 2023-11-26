package com.restbnote.rest.services;

import com.restbnote.rest.models.dto.ControleDto;

import com.restbnote.rest.models.entities.ControleEntity;

import java.util.List;


public interface ControleService {
    ControleDto createControle(final ControleDto controleDto);
    List<ControleDto> readControle();
    ControleDto updateControle(final String id, final ControleDto  controleDto);
    String deleteControle(String id);
}
