package com.example.pi_dev_4eme__poker_planning.Services;

import com.example.pi_dev_4eme__poker_planning.Entities.Reunion;
import com.example.pi_dev_4eme__poker_planning.Entities.User;
import com.example.pi_dev_4eme__poker_planning.Repositories.ReclamationRepositories;
import com.example.pi_dev_4eme__poker_planning.Repositories.ReunionRepositories;

import com.example.pi_dev_4eme__poker_planning.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReunionService implements IReunionRepositories {
    @Autowired
    UserRepository userRepositories;
    @Autowired
    ReunionRepositories reunionRepositories;
    @Autowired
    ReclamationRepositories reclamationRepositories;
    //@Override
    //public Reunion addReunion(Reunion reunion, Long idUser) {
    //  User user = userRepositories.findById(idUser).orElse(null);
    //reunion.setUser(user);
    //return  reunionRepositories.save(reunion);
    //}

    @Override
    public Reunion addReunion(Reunion reunion) {
        reunion.setDatedepot(LocalDateTime.now());


        return reunionRepositories.save(reunion);
    }

    @Override
    public void deleteReunion(Long id) {
        reunionRepositories.deleteById(id);


    }

    @Override
    public List<Reunion> getAllReunions() {
        return reunionRepositories.findAll();


    }

    @Override
    public Reunion getReunionById(Long id) {
        return reunionRepositories.findById(id).orElse(null);

    }


    @Override
    public Reunion updateReunion(Long id, Reunion updatedReunion) {

        Reunion existingReunion = reunionRepositories.findById(id).orElse(null);

        if (existingReunion != null) {
            existingReunion.setTitre_Reunion(updatedReunion.getTitre_Reunion());
            existingReunion.setDateDebut_Reunion(updatedReunion.getDateDebut_Reunion());
            existingReunion.setDateFin_Reunion(updatedReunion.getDateFin_Reunion());
            existingReunion.setLieu_Reunion(updatedReunion.getLieu_Reunion());
            existingReunion.setPriorite_Reunion(updatedReunion.getPriorite_Reunion());
            // Autres attributs à mettre à jour si nécessaire

            return reunionRepositories.save(existingReunion);
        } else {
            return null; // Gérer le cas où la réunion n'existe pas
        }
    }

    @Override
    public List<String> getAllTitreReunion() {
        return reunionRepositories.findAllTitres();
    }

    @Override
    public void addUserToReunionByUserIdAndUserNames( Reunion reunion, List<String> userNames) {

        // Ajout de l'utilisateur à la réunion
        LocalDateTime datedepot = LocalDateTime.now();
        reunion.setDatedepot(datedepot);


        // Recherche des autres utilisateurs par leur nom et ajout à la réunion
        for (String userName : userNames) {
            User invitedUser = userRepositories.findUsersByNomUser(userName);
            if (invitedUser != null) {
                reunion.getUsers().add(invitedUser);
            }
        }

        // Sauvegarde de la réunion mise à jour
        reunionRepositories.save(reunion);
    }}