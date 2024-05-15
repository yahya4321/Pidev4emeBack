package com.example.pi_dev_4eme__poker_planning.Services;

import com.example.pi_dev_4eme__poker_planning.Entities.Reclamation;
import com.example.pi_dev_4eme__poker_planning.Entities.Reunion;

import java.security.Principal;
import java.util.List;

public interface IReclamationRepositories
{
    // public void addReclamtion (Reclamation reclamation , Long idUser , Long idReunion);
    public  void deleteReclamation(Long id);
    public Reclamation addReclamtion (Reclamation reclamation , Principal connectedUser, String titre_Reunion );
    List<Reclamation> getAllReclamation();
    public Reclamation updateReclamation(Long id, Reclamation updatedReclamation);
    public Reclamation getReclamationById(Long id) ;




}