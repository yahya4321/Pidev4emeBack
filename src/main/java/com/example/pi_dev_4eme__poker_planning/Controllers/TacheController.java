package com.example.pi_dev_4eme__poker_planning.Controllers;


import com.example.pi_dev_4eme__poker_planning.Entities.Projet;
import com.example.pi_dev_4eme__poker_planning.Entities.Tache;
import com.example.pi_dev_4eme__poker_planning.Entities.User;
import com.example.pi_dev_4eme__poker_planning.Services.TacheServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Tache")
public class TacheController
{
   @Autowired
    TacheServices Tservice;
    @PostMapping("/ajouterTache")
    public ResponseEntity<Void> addTache(@RequestBody Tache tache) {
        try {
            // Fetch the user and project objects from the tache object
            User user = tache.getUser();
            Projet projet = tache.getProjet();

            // Call the addTache method in the service layer
            Tservice.addTache(tache, user, projet);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            // Handle exceptions
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @GetMapping("/calculateRemainingDays/{id}")
    public ResponseEntity<Long> calculateRemainingDays(@PathVariable("id") long id) {
        try {
            // Retrieve the task by its ID
            Tache tache = Tservice.getTachesById(id);

            // Calculate the remaining days using the service method
            long remainingDays = Tservice.calculateRemainingDays(tache);

            // Return the remaining days
            return ResponseEntity.ok(remainingDays);
        } catch (Exception e) {
            // Handle exceptions
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/GetTache/")
    List<Tache> ShowAllTache() {
        return Tservice.ShowTache();
    }
    // Endpoint to get tasks by project ID and user ID


    @PutMapping("/Updatetache/{id}")
    Tache UpdateTache(@RequestBody Tache tache, @PathVariable("id") long idtache) {
        return Tservice.UpdateTache(tache,idtache);
    }

    @DeleteMapping("/Deletetache/{id}")
    void DeleteIteration( @PathVariable("id") long idtache)
    {
        Tservice.DeleteTache(idtache);

    }
    @GetMapping("/byUser/{userId}")
    public List<Tache> getTachesByUserId(@PathVariable long userId) {
        return Tservice.getTachesByUserId(userId);
    }
    @GetMapping("/GetTache/{idTache}")
    Tache getTacheById(@PathVariable Long idTache) {
        return Tservice.getTachesById(idTache);
    }
}
