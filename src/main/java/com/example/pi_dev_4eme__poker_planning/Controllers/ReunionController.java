package com.example.pi_dev_4eme__poker_planning.Controllers;
import com.example.pi_dev_4eme__poker_planning.Entities.Reclamation;
import com.example.pi_dev_4eme__poker_planning.Entities.Reunion;
import com.example.pi_dev_4eme__poker_planning.Entities.User;
import com.example.pi_dev_4eme__poker_planning.Repositories.UserRepository;
import com.example.pi_dev_4eme__poker_planning.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
//@CrossOrigin("*")

public class ReunionController {
    @Autowired
    UserRepository userRepositories ;
    @Autowired
    IReunionRepositories iReunionRepostories ;
    @Autowired
    IReclamationRepositories reclamationRepositories ;
    @Autowired
    ReunionService reunionService ;
    @Autowired
    ReclamationService reclamationService ;
    @Autowired
    ReclamationStatsService reclamationStatsService;
    @Autowired
    UserServices userServices;
    private static final Logger logger = LoggerFactory.getLogger(UserControllers.class);


    //@PostMapping("/ajouterReunion/{id}")
    //public Reunion addReunion(@RequestBody Reunion reunion,@PathVariable  ("id") Long idUser){
    //return  iReunionRepostories.addReunion(reunion,idUser);
    //}
    @PostMapping("/ajouter")
    public Reunion addReunion (@RequestBody Reunion reunion){
        return iReunionRepostories.addReunion(reunion);
    }
//    @PutMapping("/ajouterReclamation/{idU}/{idR}")
    //  public void  addReclamtion(@RequestBody Reclamation reclamation,@PathVariable("idU") Long idUser,@PathVariable("idR") Long idReunion) {

    //reclamationRepositories.addReclamtion(reclamation,idUser,idReunion);
    //}


    @DeleteMapping("delete/{id}")
    public void deleteReunion(@PathVariable Long id) {
        iReunionRepostories.deleteReunion(id);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<Reunion>> getAllReunions() {
        List<Reunion>reunions = reunionService.getAllReunions()  ;
        return new ResponseEntity<>(reunions,HttpStatus.OK);
    }
    @GetMapping("get/{id}")
    public Reunion getReunionById(@PathVariable Long id) {
        return iReunionRepostories.getReunionById(id);
    }
    @PutMapping("update/{id}")
    public ResponseEntity<Reunion> updateReunion(@PathVariable("id") Long id, @RequestBody Reunion updatedReunion) {
        Reunion reunion = iReunionRepostories.updateReunion(id, updatedReunion);
        if (reunion != null) {
            return new ResponseEntity<>(reunion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("delreclamation/{id}")
    public  void deleteReclamation(@PathVariable Long id){
        reclamationRepositories.deleteReclamation(id);
    }

    @PostMapping("/add/{titre_Reunion}")
    public ResponseEntity<?> addReclamtion(@RequestBody Reclamation reclamation, @PathVariable("titre_Reunion") String titre_Reunion ,Principal connectedUser) {
        try {
            Reclamation addedReclamation = reclamationService.addReclamtion(reclamation,connectedUser ,titre_Reunion);
            return ResponseEntity.ok(addedReclamation);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/het")
    public List<String> getAllTitreReunion(){
        return reunionService.getAllTitreReunion();
    }
    @GetMapping("affichage")
    public List<Reclamation> getAllReclamation() {

        return reclamationService.getAllReclamation();


    }
    @GetMapping("/countByReunion")
    public List<Object[]> getReclamationCountByReunion() {
        return reclamationStatsService.getReclamationCountByReunion();
    }
    @PostMapping  ("/users/reunion")
    public ResponseEntity<String> addUserToReunionByUserIdAndUserNames(@RequestBody Reunion reunion,
                                                                       @RequestParam List<String> userNames) {
        try {
            reunionService.addUserToReunionByUserIdAndUserNames( reunion, userNames);
            return ResponseEntity.ok("La réunion a été ajoutée et les utilisateurs ont été invités avec succès.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }




    @PutMapping("updateRec/{id}")
    public ResponseEntity<Reclamation> updateReclamation(@PathVariable("id") Long id, @RequestBody Reclamation updatedReclamtion) {
        Reclamation reclamation = reclamationService.updateReclamation(id, updatedReclamtion);
        if (reclamation != null) {
            return new ResponseEntity<>(reclamation, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("getRec/id")
    public Reclamation getReclamationById(@PathVariable Long id) {
        return reclamationService.getReclamationById(id);


    }}
