package com.restbnote.rest.models.entities;

import com.restbnote.rest.models.Id.MatiereId;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@IdClass(MatiereId.class)
@Table(name = "matieres")
@Data
public class MatiereEntity {

    @Id
    @Column
    private String nomMatiereID;

    @Id
    @Column
    private String mailProfsID;
}


