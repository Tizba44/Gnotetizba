package com.restbnote.rest.models.dto;


import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class ControleDto {
    private String id;
    private LocalDate date;
    private Integer note;
    private Integer coef;
    private String appreciation;
    private String intituleID;
    private String matiereID;
    private String mailEtudiantsID;
}





