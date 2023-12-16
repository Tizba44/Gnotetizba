package com.restbnote.rest.models.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

@Data
@Builder
public class AdminDto  extends RepresentationModel<AdminDto> {
    private String id;
    private String adminMailID;
    private String adminPassword;


}
