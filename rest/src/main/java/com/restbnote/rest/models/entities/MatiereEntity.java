package com.restbnote.rest.models.entities;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
@Data
@Entity
@Table(name = "matieres")
public class MatiereEntity {
    @Id
    @GeneratedValue( generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @Column
    private String nomMatiereID;
    @Column
    private String mailProfsID;
}


