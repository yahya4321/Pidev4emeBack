package com.example.pi_dev_4eme__poker_planning.Repositories;

import com.example.pi_dev_4eme__poker_planning.Entities.Reunion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReunionRepositories extends JpaRepository<Reunion,Long> {
    @Query("SELECT r.titre_Reunion FROM Reunion r")
    List<String> findAllTitres();
    @Query("SELECT r FROM Reunion r WHERE r.titre_Reunion = :titre")

    Reunion findReunionByTitre(@Param("titre") String titre);
}