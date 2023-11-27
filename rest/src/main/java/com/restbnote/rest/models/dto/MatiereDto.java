package com.restbnote.rest.models.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MatiereDto {
    private String id;
    private String nomMatiereID;
    private String mailProfsID;
}
