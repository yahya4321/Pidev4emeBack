package com.example.pi_dev_4eme__poker_planning.Entities;

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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "feedbacks")
public class FeedBack implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idfeedback;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(nullable = false)
    private int evaluation;

    @Column(nullable = false)
    private String description;

    private String role_feedback;

    private Boolean anonyme;
    private String hhvh;
    @OneToOne
    @JoinColumn(name = "survey_id", referencedColumnName = "idsurvey")
    private Survey survey;
    @ManyToOne
    Session session;
}
