package com.example.pi_dev_4eme__poker_planning.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class SprintBacklog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSprintBacklog;
    private String nomBacklog;
    private int effortEstimation;
    private String definitionOfDone;
    private int priorite;
    private boolean estTermine;
    private Date dateDebut;
    private Date dateFin;
    @JsonIgnore
    @ManyToOne
    private Sprint sprint;
    @JsonIgnore
    @OneToMany(mappedBy = "sprintBacklog",cascade = CascadeType.ALL)
    private List<UserStory> userStories;



}