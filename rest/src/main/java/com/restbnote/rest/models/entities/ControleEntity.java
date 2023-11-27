package com.restbnote.rest.models.entities;

import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Data
@Entity
@Table(name = "controles")
public class ControleEntity {
    @Id
    @GeneratedValue( generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")

    private String id;
    @Column
    private LocalDate date;
    @Column
    private Integer note;
    @Column
    private Integer coef;
    @Column
    private String appreciation;
    @Column
    private String intituleID;
    @Column
    private String matiereID;
    @Column
    private String mailEtudiantsID;
}
