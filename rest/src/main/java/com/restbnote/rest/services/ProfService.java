package com.restbnote.rest.services;

import java.util.List;
import com.restbnote.rest.models.dto.ProfDto;

public interface ProfService {
    ProfDto createProf(final ProfDto profDto);
    List<ProfDto> readProf();
    ProfDto updateProf(final String id, final ProfDto  profDto);
    String deleteProf(String id);
}
