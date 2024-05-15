package com.example.pi_dev_4eme__poker_planning.Repositories;

import com.example.pi_dev_4eme__poker_planning.Entities.Estimation;
import com.example.pi_dev_4eme__poker_planning.Entities.Iteration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EstimationRepositories extends JpaRepository<Estimation,Long> {

    List<Estimation> findEstimationsByIteration_IdIteration(long idIteration);
    List<Estimation> findEstimationsByIteration(Iteration iteration);
    @Query("SELECT e FROM Estimation e ORDER BY e.id_Estimation DESC LIMIT 1")
    Estimation findLastEstimation();
}