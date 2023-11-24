package com.restbnote.rest.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "matieres")
@Data
public class MatiereEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nomMatiereID;
    @Column
    private String mailProfs;
}
