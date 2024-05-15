package com.example.pi_dev_4eme__poker_planning.Services;

import com.example.pi_dev_4eme__poker_planning.Entities.*;
import com.example.pi_dev_4eme__poker_planning.Repositories.SprintBacklogRepository;
import com.example.pi_dev_4eme__poker_planning.Repositories.TacheTechniqueRepository;
import com.example.pi_dev_4eme__poker_planning.Repositories.UserRepository;
import com.example.pi_dev_4eme__poker_planning.Repositories.UserStoryRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TacheTechniqueServiceImpl implements ITacheTechniqueServices {

    @Autowired
    private TacheTechniqueRepository tacheTechniqueRepository;

    @Autowired
    private UserStoryRepository userStoryRepository;
    @Autowired
    private UserRepository userRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public TacheTechnique createTacheTechnique(TacheTechnique tacheTechnique) {
        // Logique pour créer une tâche technique indépendamment de son affectation à une user story
        tacheTechnique.setDateCreation(new Date());
        return tacheTechniqueRepository.save(tacheTechnique);
    }

    @Override
    public TacheTechnique affecterTacheTechnique(Long userStoryId, TacheTechnique tacheTechnique) throws ChangeSetPersister.NotFoundException {
        UserStory userStory = userStoryRepository.findById(userStoryId)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        tacheTechnique.setUserStory(userStory);
        tacheTechnique.setDateCreation(new Date());
        return tacheTechniqueRepository.save(tacheTechnique);
    }

    @Override
    public TacheTechnique updateTacheTechnique(Long id, TacheTechnique tacheTechnique) throws ChangeSetPersister.NotFoundException {
        if (tacheTechniqueRepository.existsById(id)) {
            tacheTechnique.setIdTacheTechnique(id);
            tacheTechnique.setDateModification(new Date());
            return tacheTechniqueRepository.save(tacheTechnique);
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public List<TacheTechnique> getAllTacheTechniques() {
        return tacheTechniqueRepository.findAll();
    }

    @Override
    public TacheTechnique getTacheTechniqueById(Long id) throws ChangeSetPersister.NotFoundException {
        return tacheTechniqueRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }

    @Override
    public void deleteTacheTechnique(Long id) throws ChangeSetPersister.NotFoundException {
        if (tacheTechniqueRepository.existsById(id)) {
            tacheTechniqueRepository.deleteById(id);
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public void unassignTacheTechnique(Long tacheTechniqueId) {
        Optional<TacheTechnique> optionalTacheTechnique = tacheTechniqueRepository.findById(tacheTechniqueId);

        if (optionalTacheTechnique.isPresent()) {
            TacheTechnique tacheTechnique = optionalTacheTechnique.get();
            tacheTechnique.setUserStory(null);  // Désaffecter en mettant la UserStory à null
            tacheTechniqueRepository.save(tacheTechnique);
        } else {
        }
    }
    @Override
    public void affecterUtilisateurATacheTechnique(Long idTacheTechnique, Long idUtilisateur) {
        // Rechercher la tâche technique
        TacheTechnique tacheTechnique = tacheTechniqueRepository.findById(idTacheTechnique)
                .orElseThrow(() -> new EntityNotFoundException("Tâche technique non trouvée"));

        // Réinitialiser l'utilisateur affecté à null
        tacheTechnique.setDevAffectee(null);

        // Rechercher l'utilisateur
        User utilisateur = userRepository.findById(idUtilisateur)
                .orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé"));

        // Vérifier si la tâche technique est déjà affectée à un utilisateur
        if (tacheTechnique.getDevAffectee() != null) {
            throw new IllegalStateException("La tâche technique est déjà affectée à un utilisateur");
        }

        // Affecter le nouvel utilisateur à la tâche technique
        tacheTechnique.setDevAffectee(utilisateur);

        // Enregistrer les modifications dans la base de données
        tacheTechniqueRepository.save(tacheTechnique);
    }

    @Override
    public List<TacheTechnique> getAllTacheTechniqueByUserStoryId(Long userStoryId) {
        return entityManager.createQuery(
                        "SELECT t FROM TacheTechnique t WHERE t.userStory.IdUserStory = :userStoryId", TacheTechnique.class)
                .setParameter("userStoryId", userStoryId)
                .getResultList();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Override
    public List<TacheTechnique> obtenirTachesTechniquesParDevAffecteeId(Long devAffecteeId) {
        return tacheTechniqueRepository.findAllByDevAffecteeIdUser(devAffecteeId);
    }

    public List<TacheTechnique> searchAdvanced(String query) {
        return tacheTechniqueRepository.findByNomTacheTechniqueContainingOrDescriptionTacheTechniqueContaining(query, query);
    }



    public List<User> getDevelopers() {
        return userRepository.findByRole(Role.valueOf("developer"));
    }


    public Map<StatutTacheTechnique, Long> getNombreTachesParStatut(Long idDev) {
        // Récupérer les tâches techniques associées au développeur
        List<TacheTechnique> tachesDuDev = tacheTechniqueRepository.findByDevAffecteeIdUser(idDev);

        // Initialiser un compteur pour chaque statut
        Map<StatutTacheTechnique, Long> compteurParStatut = new HashMap<>();
        for (StatutTacheTechnique statut : StatutTacheTechnique.values()) {
            compteurParStatut.put(statut, 0L);
        }

        // Compter le nombre de tâches pour chaque statut
        for (TacheTechnique tache : tachesDuDev) {
            StatutTacheTechnique statut = tache.getStatut_TT();
            compteurParStatut.put(statut, compteurParStatut.get(statut) + 1);
        }

        return compteurParStatut;
    }
}