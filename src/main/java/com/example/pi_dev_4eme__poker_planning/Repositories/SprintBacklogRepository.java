package com.example.pi_dev_4eme__poker_planning.Repositories;

import com.example.pi_dev_4eme__poker_planning.Entities.Sprint;
import com.example.pi_dev_4eme__poker_planning.Entities.SprintBacklog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SprintBacklogRepository extends JpaRepository<SprintBacklog, Long> {
    List<SprintBacklog> findBySprintIdSprint(long sprintId);
    long countBySprintIdSprint(Long sprintId);
    long countBySprintIdSprintAndAndEstTermine(Long sprintId, boolean estTermine);
    List<SprintBacklog> findByDateDebutAfterAndDateFinBefore(Date startDate, Date endDate);

    List<SprintBacklog> findByDateDebutAfter(Date startDate);

    List<SprintBacklog> findByDateFinBefore(Date endDate);



}