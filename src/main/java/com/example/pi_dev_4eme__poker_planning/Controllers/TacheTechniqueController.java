package com.example.pi_dev_4eme__poker_planning.Controllers;

import com.example.pi_dev_4eme__poker_planning.Entities.StatutTacheTechnique;
import com.example.pi_dev_4eme__poker_planning.Entities.TacheTechnique;
import com.example.pi_dev_4eme__poker_planning.Entities.User;
import com.example.pi_dev_4eme__poker_planning.Repositories.UserRepository;
import com.example.pi_dev_4eme__poker_planning.Services.TacheTechniqueServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/api/tacheTechniques")
public class TacheTechniqueController {

    @Autowired
    TacheTechniqueServiceImpl tacheTechniqueService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<TacheTechnique> createTacheTechnique(@RequestBody TacheTechnique tacheTechnique) {
        TacheTechnique createdTacheTechnique = tacheTechniqueService.createTacheTechnique(tacheTechnique);
        return new ResponseEntity<>(createdTacheTechnique, HttpStatus.CREATED);
    }

    @PutMapping("/assignToUserStory/{userStoryId}")
    public ResponseEntity<TacheTechnique> assignTacheTechniqueToUserStory(@PathVariable Long userStoryId,
                                                                          @RequestBody TacheTechnique tacheTechnique) throws ChangeSetPersister.NotFoundException {
        TacheTechnique assignedTacheTechnique = tacheTechniqueService.affecterTacheTechnique(userStoryId, tacheTechnique);
        return new ResponseEntity<>(assignedTacheTechnique, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TacheTechnique> updateTacheTechnique(@PathVariable Long id,
                                                               @RequestBody TacheTechnique tacheTechnique) throws ChangeSetPersister.NotFoundException {
        TacheTechnique updatedTacheTechnique = tacheTechniqueService.updateTacheTechnique(id, tacheTechnique);
        return new ResponseEntity<>(updatedTacheTechnique, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TacheTechnique>> getAllTacheTechniques() {
        List<TacheTechnique> tacheTechniques = tacheTechniqueService.getAllTacheTechniques();
        return new ResponseEntity<>(tacheTechniques, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TacheTechnique> getTacheTechniqueById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        TacheTechnique tacheTechnique = tacheTechniqueService.getTacheTechniqueById(id);
        return new ResponseEntity<>(tacheTechnique, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTacheTechnique(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        tacheTechniqueService.deleteTacheTechnique(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{tacheTechniqueId}/unassign")
    public ResponseEntity<?> unassignTacheTechnique(@PathVariable Long tacheTechniqueId) {
        try {
            tacheTechniqueService.unassignTacheTechnique(tacheTechniqueId);
            return ResponseEntity.ok().body("{\"message\": \"TacheTechnique désaffectée avec succès.\"}");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
    @GetMapping("/userStory/{userStoryId}")
    public ResponseEntity<List<TacheTechnique>> getAllTacheTechniqueByUserStoryId(@PathVariable Long userStoryId) {
        List<TacheTechnique> tacheTechniques = tacheTechniqueService.getAllTacheTechniqueByUserStoryId(userStoryId);
        return new ResponseEntity<>(tacheTechniques, HttpStatus.OK);
    }


    @PutMapping("/tache/{idTache}/utilisateur/{idUtilisateur}")
    public ResponseEntity<String> affecterUtilisateurATache(@PathVariable Long idTache, @PathVariable Long idUtilisateur) {
        try {
            tacheTechniqueService.affecterUtilisateurATacheTechnique(idTache, idUtilisateur);
            return ResponseEntity.ok().body("{\"message\": \"user affectee avec succès.\"}");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tâche technique ou utilisateur non trouvé.");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La tâche technique est déjà affectée à un utilisateur.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur est survenue lors de l'affectation de l'utilisateur à la tâche technique.");
        }
    }


    @GetMapping("/searchAdvanced")
    public List<TacheTechnique> searchAdvanced(@RequestParam String query) {
        return tacheTechniqueService.searchAdvanced(query);
    }
    @GetMapping("/dev/{devAffecteeId}")
    public List<TacheTechnique> obtenirTachesTechniquesParDevAffecteeId(@PathVariable Long devAffecteeId) {
        return tacheTechniqueService.obtenirTachesTechniquesParDevAffecteeId(devAffecteeId);
    }
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = tacheTechniqueService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/{id}/taches")
    public Map<StatutTacheTechnique, Long> getNombreTachesParStatut(@PathVariable("id") Long idDev) {
        return tacheTechniqueService.getNombreTachesParStatut(idDev);
    }


    @GetMapping("/developpeurs")
    public List<User> getDevelopers() {
        return tacheTechniqueService.getDevelopers();
    }
}