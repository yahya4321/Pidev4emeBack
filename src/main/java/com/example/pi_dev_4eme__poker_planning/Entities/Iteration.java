package com.example.pi_dev_4eme__poker_planning.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Iteration implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIteration;
    @Enumerated(EnumType.STRING)
    private ResultatIteration resultat;

    private Date date_IterationDebut;
    private Date date_IterationFin;
    @OneToOne
    @JsonIgnore
    private Chat chat;


    @OneToMany(mappedBy = "iteration",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Estimation> Estimations = new ArrayList<>();

    //hethy fil entites Iteration
    @ManyToOne
    @JsonIgnore
    Session session;

}