package com.restbnote.rest.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminDto {
    private String adminMailID;
    private String adminPassword;
}
