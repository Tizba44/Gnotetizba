package com.restbnote.rest.models.entities;

import jakarta.persistence.Entity;
import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.persistence.GenerationType;


@Entity
@Table(name = "profs")
@Data
public class ProfEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String mailID;
    @Column
    private String nom;
    @Column
    private String prenom;
    @Column
    private String telephone;
    @Column
    private String motDePasse;
}

