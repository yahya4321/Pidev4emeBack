package com.example.pi_dev_4eme__poker_planning.Entities;
import jakarta.persistence.*;
import lombok.*;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.hibernate.annotations.AttributeAccessor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "surveys")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idsurvey;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @OneToOne(mappedBy = "survey")
    private FeedBack feedback;

    @OneToMany(mappedBy = "survey")
    private List<Question> questionList;
}