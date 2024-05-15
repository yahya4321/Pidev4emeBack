package com.example.pi_dev_4eme__poker_planning.Repositories;

import com.example.pi_dev_4eme__poker_planning.Entities.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReclamationRepositories extends JpaRepository <Reclamation,Long> {
    @Query("SELECT r FROM Reclamation r ORDER BY r.dateSoumission DESC")
    Reclamation findTopByOrderByDateSoumissionDesc();
    @Query("SELECT r.reunionReclamer.titre_Reunion, COUNT(r) FROM Reclamation r GROUP BY r.reunionReclamer.titre_Reunion")
    List<Object[]> getReclamationCountByReunion();
}