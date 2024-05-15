package com.example.pi_dev_4eme__poker_planning.Controllers;

import com.example.pi_dev_4eme__poker_planning.Entities.Projet;
import com.example.pi_dev_4eme__poker_planning.Services.IProjetService;
import com.example.pi_dev_4eme__poker_planning.Services.ProjetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Projet")
public class ProjetController {
    @Autowired
    IProjetService projetService;

   /* @PostMapping("/addprojet")
    Projet addProjet(@RequestBody Projet projet){
        return projetService.addProject(projet);
    }*/

    /*@PostMapping("/addprojets")
    List<Projet> addProjets(@RequestBody List<Projet> projets){
        return projetService.saveProjets(projets);*/

    @GetMapping("/getprojet")
    public List<Projet> getAllProjets() {

        return projetService.getAllProjets();
    }

    @GetMapping("getprojets/{idProjet}")
    public Projet getProjetById(@PathVariable Long idProjet) {

        return projetService.getProjetById(idProjet).orElse(null);
    }

    @PostMapping("/createprojet")
    public Projet createOrUpdateProjet(@RequestBody Projet projet) {
        return projetService.saveOrUpdateProjet(projet);
    }

    @DeleteMapping("/deleteprojet/{idProjet}")
    public void deleteProjet(@PathVariable Long idProjet) {
        projetService.deleteProjet(idProjet);
    }
    @PutMapping("updateprojet/{idProjet}")
    public Projet updateProjet(@PathVariable Long idProjet, @RequestBody Projet projet) {
        return projetService.updateProjet(idProjet, projet);
    }


}