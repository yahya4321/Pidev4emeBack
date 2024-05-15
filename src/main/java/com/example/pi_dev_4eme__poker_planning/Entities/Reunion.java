package com.example.pi_dev_4eme__poker_planning.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reunion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idReunion ;
    String titre_Reunion;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime DateDebut_Reunion;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime DateFin_Reunion;

    String lieu_Reunion;
    String priorite_Reunion;
    LocalDateTime datedepot;

    @ManyToOne
    @JsonBackReference


    User user ;
    @ManyToMany
    @JsonIgnore

    Set<User> users = new HashSet<>();
    @OneToMany (mappedBy = "reunionReclamer",cascade = CascadeType.ALL)
    @JsonIgnore
    Set<Reclamation>reclamationSet;
}