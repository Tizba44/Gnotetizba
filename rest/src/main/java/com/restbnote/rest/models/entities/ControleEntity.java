package com.restbnote.rest.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "controles")
@Data
public class ControleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String date;
    @Column
    private Integer note;
    @Column
    private Integer coef;
    @Column
    private String mailID;
    @Column
    private String appreciation;
    @Column
    private String intitule;
    @Column
    private String nomMatiereID;
}
