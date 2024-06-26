package com.restbnote.rest.services;

import com.restbnote.rest.models.dto.ControleDto;
import java.util.List;

public interface ControleService {
    ControleDto createControle(final ControleDto controleDto);
    List<ControleDto> readControle();
    ControleDto readOneControle(final String id);
    ControleDto updateControle(final String id, final ControleDto  controleDto);
    String deleteControle(String id);
    List<ControleDto> readAllControlesOfEtudiant(String mailEtudiantsID);
}


