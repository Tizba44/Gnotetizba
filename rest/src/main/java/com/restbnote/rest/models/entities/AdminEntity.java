package com.restbnote.rest.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "admins")
@Data
public class AdminEntity {
    @Id
    @GeneratedValue( generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")

    private String id;
    @Column
    private String adminMailID;
    @Column
    private String adminPassword;
}
