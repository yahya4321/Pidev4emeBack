package com.example.pi_dev_4eme__poker_planning.Entities;



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
public class Session implements Serializable {

    @Id
    @GeneratedValue
    private Long idSession;

    private String NomSession;
    private String Description;
    private Date DateEtHeureDebut ;
    private Date DateEtHeureFin ;
    private String codeSession;
    public String LinkSession;

    @Enumerated(EnumType.STRING)
    private StatutDeSession statut;

    @Enumerated(EnumType.STRING)
    private TypeDeVote vote;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "session")
    private Set<FeedBack> feedBack;




    @OneToMany(cascade = CascadeType.ALL,mappedBy = "session")
    private Set<Iteration> iteration;




    //@OneToMany(cascade = CascadeType.ALL,mappedBy = "session")
    //private Set<UserStory> UserStory;

    /*@ManyToMany
    @JoinTable(name = "session_user",
            joinColumns = @JoinColumn(name = "session_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;
    */
}
