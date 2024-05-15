package com.example.pi_dev_4eme__poker_planning.Services;

import com.example.pi_dev_4eme__poker_planning.Entities.Reunion;

import java.util.List;

public interface IReunionRepositories {
    // public Reunion addReunion (Reunion reunion, Long idUser);
    public Reunion addReunion (Reunion reunion);
    public  void deleteReunion(Long id);
    List<Reunion> getAllReunions();
    Reunion getReunionById(Long id);
    Reunion updateReunion(Long id, Reunion updatedReunion);
    public List<String> getAllTitreReunion();
    public void addUserToReunionByUserIdAndUserNames( Reunion reunion, List<String> userNames);


}