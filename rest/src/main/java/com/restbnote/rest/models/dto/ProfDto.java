    package com.restbnote.rest.models.dto;

    import lombok.Builder;
    import lombok.Data;


    @Data
    @Builder
    public class ProfDto {
        private Long id;
        private String mailID;
        private String nom;
        private String prenom;
        private String telephone;
        private String motDePasse;
    }

