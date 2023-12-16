package com.restbnote.rest.models.dto;


import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import org.springframework.hateoas.RepresentationModel;


@Data
@Builder
public class ControleDto extends RepresentationModel<ControleDto> {
    private String id;
    private LocalDate date;
    private Integer note;
    private Integer coef;
    private String appreciation;
    private String intituleID;
    private String matiereID;
    private String mailEtudiantsID;
}





