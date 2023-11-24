package com.restbnote.rest.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "etudiants")
@Data
public class EtudiantEntity {
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
}
