package com.restbnote.rest.models.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
public class EtudiantDto extends RepresentationModel<EtudiantDto> {
    private String id;
    private String mailID;
    private String nom;
    private String prenom;
    private String telephone;
}
