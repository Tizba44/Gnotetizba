package com.restbnote.rest.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "admins")
@Data
public class AdminEntity {
    @Id
    @Column
    private String adminMailID;
    @Column
    private String adminPassword;
}
