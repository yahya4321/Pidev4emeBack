package com.example.pi_dev_4eme__poker_planning.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
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


public class Estimation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Estimation;

    private int valeur;
    private Date dateVote;



    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="idIteration",referencedColumnName = "idIteration",
            insertable = true,updatable = true)
    private Iteration iteration;


    @OneToOne
    @JsonIgnore
    @JoinColumn(name="idUser",referencedColumnName = "idUser",
            insertable = true,updatable = true  )

    private User user;
}