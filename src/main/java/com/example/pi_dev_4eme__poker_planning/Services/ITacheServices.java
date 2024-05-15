package com.example.pi_dev_4eme__poker_planning.Services;

import com.example.pi_dev_4eme__poker_planning.Entities.Projet;
import com.example.pi_dev_4eme__poker_planning.Entities.Tache;
import com.example.pi_dev_4eme__poker_planning.Entities.User;

import java.util.List;

public interface ITacheServices {
    void addTache(Tache tache, User user, Projet projet);

    Tache UpdateTache(Tache tache, long idtache);

    List<Tache> ShowTache();

    void DeleteTache(long idtache);

    List<Tache> ShowTacheWithUserAndProjet();
    long calculateRemainingDays(Tache tache);

}
