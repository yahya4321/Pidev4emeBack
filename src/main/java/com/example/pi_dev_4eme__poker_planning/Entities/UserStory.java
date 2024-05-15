package com.example.pi_dev_4eme__poker_planning.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserStory implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long IdUserStory;
    String Titre_US;
    String Description_US;
    @Enumerated(EnumType.STRING)
    StatutUserStory Statut_US;
    int Velocite_US;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    Projet projet;
    @ManyToOne
    @JsonIgnore
    SprintBacklog sprintBacklog;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    List<TacheTechnique> tacheTechniques;

    //hethy fil entites UserStory
    @JsonIgnore
    @ManyToOne
    Session session;

}