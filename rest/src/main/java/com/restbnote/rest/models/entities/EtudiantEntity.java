package com.restbnote.rest.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
@Data
@Entity
@Table(name = "etudiants")
public class EtudiantEntity {
    @Id
    @GeneratedValue( generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @Column
    private String mailID;
    @Column
    private String nom;
    @Column
    private String prenom;
    @Column
    private String telephone;
}
