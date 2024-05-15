package com.example.pi_dev_4eme__poker_planning.Entities;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Projet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idProjet;
    String Nom_Projet;
    String Description_Projet;
    Date DateDebut_Projet;
    Date DateFin_Projet;

    public Projet(int idProjet) {
        this.idProjet = idProjet;
    }

    @OneToMany(mappedBy = "projet", cascade = CascadeType.ALL)

    @JsonIgnore
    private List<Tache> taches;

    @OneToMany(mappedBy = "projet",cascade = CascadeType.ALL)
    @JsonIgnore

    Set<UserStory> userStory;

   /* @ManyToMany(mappedBy = "projets",cascade = CascadeType.ALL)
    Set<User> Users;*/
}
