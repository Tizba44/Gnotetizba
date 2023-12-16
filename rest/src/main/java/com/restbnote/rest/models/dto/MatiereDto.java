package com.restbnote.rest.models.dto;

import org.springframework.hateoas.RepresentationModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MatiereDto extends RepresentationModel<MatiereDto> {
    private String id;
    private String nomMatiereID;
    private String mailProfsID;
}
