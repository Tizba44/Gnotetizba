package com.restbnote.rest.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ControleDto {
    private Long id;
    private String date;
    private Integer note;
    private Integer coef;
    private String mailID;
    private String appreciation;
    private String intitule;
    private String nomMatiereID;
}
