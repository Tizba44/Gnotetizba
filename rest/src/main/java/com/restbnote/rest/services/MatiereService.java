package com.restbnote.rest.services;


import com.restbnote.rest.models.dto.MatiereDto;
import java.util.List;



public interface MatiereService {


    MatiereDto createMatiere(final MatiereDto matiereDto);
    List<MatiereDto> readMatiere();
    MatiereDto updateMatiere(final String id, final MatiereDto  matiereDto);
    String deleteMatiere(String id);


}
