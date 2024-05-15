package com.example.pi_dev_4eme__poker_planning.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reclamation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idReclamation;
    String titre_Reclamation;
    String contenu_Reclamation;
    LocalDateTime dateSoumission;
    //yasssin
    @ManyToOne
    @JoinColumn(name = "idUser",referencedColumnName = "idUser",
            insertable = true,updatable = false)
    @JsonIgnore
    User userReclamer;

    @ManyToOne

    @JoinColumn(name = "idReunion" ,referencedColumnName = "idReunion",
            insertable = true,updatable = false)
    @JsonIgnore
    Reunion reunionReclamer;

}