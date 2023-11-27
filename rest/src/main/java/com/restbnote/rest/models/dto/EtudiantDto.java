package com.restbnote.rest.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EtudiantDto {
    private String id;
    private String mailID;
    private String nom;
    private String prenom;
    private String telephone;
}
