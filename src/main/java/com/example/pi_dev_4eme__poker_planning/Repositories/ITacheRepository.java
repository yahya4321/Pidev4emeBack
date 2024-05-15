package com.example.pi_dev_4eme__poker_planning.Repositories;

import com.example.pi_dev_4eme__poker_planning.Entities.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ITacheRepository extends JpaRepository<Tache,Long> {

    Tache findTachesByIdTache(long idtache);
    @Query("SELECT t FROM Tache t LEFT JOIN FETCH t.user LEFT JOIN FETCH t.projet")
    List<Tache> findAllWithUserAndProjet();
    @Query("SELECT t FROM Tache t LEFT JOIN FETCH t.user u WHERE u.id = ?1")
    List<Tache> findByUserId(long userId);

}
