package com.example.pi_dev_4eme__poker_planning.Services;

import com.example.pi_dev_4eme__poker_planning.Entities.TacheTechnique;
import com.example.pi_dev_4eme__poker_planning.Entities.User;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface ITacheTechniqueServices {
    TacheTechnique updateTacheTechnique(Long id, TacheTechnique tacheTechnique) throws ChangeSetPersister.NotFoundException;
    List<TacheTechnique> getAllTacheTechniques();
    TacheTechnique getTacheTechniqueById(Long id) throws ChangeSetPersister.NotFoundException;
    void deleteTacheTechnique(Long id) throws ChangeSetPersister.NotFoundException;
    TacheTechnique affecterTacheTechnique(Long userStoryId, TacheTechnique tacheTechnique) throws ChangeSetPersister.NotFoundException;
    TacheTechnique createTacheTechnique(TacheTechnique tacheTechnique) ;
    void affecterUtilisateurATacheTechnique(Long idTacheTechnique, Long idUtilisateur) ;
   List<TacheTechnique> getAllTacheTechniqueByUserStoryId(Long userStoryId) ;
    List<User> getAllUsers();
    public List<TacheTechnique> obtenirTachesTechniquesParDevAffecteeId(Long devAffecteeId) ;
    void unassignTacheTechnique(Long tacheTechniqueId);
}