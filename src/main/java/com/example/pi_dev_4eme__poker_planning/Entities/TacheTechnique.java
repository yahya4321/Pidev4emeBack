package com.example.pi_dev_4eme__poker_planning.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class TacheTechnique implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTacheTechnique;

    private String nomTacheTechnique;  // Nom ou description de la tâche technique
    private String descriptionTacheTechnique;  // Description détaillée de la tâche technique

    @ManyToOne
    @JoinColumn(name = "user_story_id")
    @JsonIgnore


    private UserStory userStory;  // User story liée à la tâche technique// //   mnha hia najjm na3rf chkoun les acteurs li fil projet li na5dmo alih . donc affectation des taches technique ykoun ashel w zeda  na3ml deaffectaion pour un dev specifique en tant que scrum master 5ater ya3rf progression des taches celon le type enum
    private Date dateCreation;  // Date de création de la tâche technique     //    tnjm tkoun automatique kif na3ml ajout yt3ba direct champ date de creation
    private Date dateModification;  // Date de la dernière modification de la tâche technique
    // si le scrum master veux modifier une tache technique deja affecte a un developpeur il peut , aussi une notif en temps reel elle va etre envoyee d'une maniere securise au dev qui a cette tache technique

    @Enumerated(EnumType.STRING)
    StatutTacheTechnique Statut_TT;

    private long currentUserId;
   
    @ManyToOne
    private User devAffectee;
}