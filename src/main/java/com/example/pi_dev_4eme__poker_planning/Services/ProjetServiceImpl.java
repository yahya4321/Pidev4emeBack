package com.example.pi_dev_4eme__poker_planning.Services;

import com.example.pi_dev_4eme__poker_planning.Entities.Projet;
import com.example.pi_dev_4eme__poker_planning.Entities.UserStory;
import com.example.pi_dev_4eme__poker_planning.Repositories.ProjetRepositorie;
import com.example.pi_dev_4eme__poker_planning.Repositories.UserStoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProjetServiceImpl implements IProjetService {

    @Autowired
    ProjetRepositorie projetRepositorie;
    @Autowired
    UserStoryRepository userStoryRepository;
    @Override
    public List<Projet> getAllProjets() {
        return projetRepositorie.findAll();
    }

    @Override
    public Optional<Projet> getProjetById(Long idProjet) {
        return projetRepositorie.findById(idProjet);
    }

    @Override
    public Projet saveOrUpdateProjet(Projet projet) {
        List<UserStory> uss = new ArrayList<>();
        uss = (List<UserStory>) projet.getUserStory();
        for(UserStory i:uss)
        {
            i.setProjet(projet);
        }
        return projetRepositorie.save(projet);
    }

    @Override
    public void deleteProjet(Long idProjet) {
        projetRepositorie.deleteById(idProjet);
    }

    public Projet updateProjet(Long id, Projet projet) {
        Optional<Projet> existingProjetOptional = projetRepositorie.findById(id);
        if (existingProjetOptional.isPresent()) {
            Projet existingProjet = existingProjetOptional.get();
            // Mettre à jour les champs nécessaires du projet existant
            existingProjet.setNom_Projet(projet.getNom_Projet());
            existingProjet.setDescription_Projet(projet.getDescription_Projet());
            existingProjet.setDateDebut_Projet(projet.getDateDebut_Projet());
            existingProjet.setDateFin_Projet(projet.getDateFin_Projet());
            // Enregistrer les modifications dans la base de données
            return projetRepositorie.save(existingProjet);
        } else {
            // Gérer le cas où le projet n'est pas trouvé
            return null;
        }







   /* @Override
    public Projet addProject(Projet projet) {
        return projetRepositorie.save(projet);
    }
    public List<Projet> saveProjets(List<Projet> projets){
        return projetRepositorie.findAll();
    }

    @Override
    public Projet validateAndGetProjet(Long IdProjet) {
        return projetRepositorie.findById(IdProjet).orElse(null);
    }

    @Override
    public void deleteProjet(Projet projet) {
        projetRepositorie.delete(projet);
    }

    public List<Projet> getProjets(){
        return  projetRepositorie.findAll();
    }
   public Projet getProjetsByIdProjet(Long IdProjet){
        return projetRepositorie.findById(IdProjet).orElse(null);
   }
    //public Projet getProjetsByNom_Projet(String Nom_Projet){
        //return projetRepositories.findByNom_Projet(Nom_Projet);

    public String deleteProjet(Long IdProjet){
        projetRepositorie.deleteById(IdProjet);
        return "Projet Supprimé !! "+IdProjet;
    }
    public Projet updateProjet(Projet projet){
        Projet existingProjet=projetRepositorie.findByIdProjet(projet.getIdProjet());
        existingProjet.setNom_Projet(projet.getNom_Projet());
        existingProjet.setDescription_Projet(projet.getDescription_Projet());
        existingProjet.setDateDebut_Projet(projet.getDateDebut_Projet());
        existingProjet.setDateFin_Projet(projet.getDateFin_Projet());
        return projetRepositorie.save(existingProjet);
    }*/
    }



}