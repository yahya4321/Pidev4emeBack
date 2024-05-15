package com.example.pi_dev_4eme__poker_planning.Entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Tache  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idTache ;
    private String description;



    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDebutTache;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFinTache;
    private  long RemainingDays;
    @Enumerated(EnumType.STRING)
    RoleProjet tacheProjet;

    private boolean estTerminee;

    @Enumerated(EnumType.STRING)
    private Location lieu;

    @ManyToOne
    @JoinColumn(name = "idProjet", referencedColumnName = "idProjet",
            insertable = true, updatable = false)
    private Projet projet;

    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "idUser",
            insertable = true, updatable = false)

    private User user;
}
