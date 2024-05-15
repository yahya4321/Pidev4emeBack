package com.example.pi_dev_4eme__poker_planning.Services;

import com.example.pi_dev_4eme__poker_planning.Entities.Projet;
import com.example.pi_dev_4eme__poker_planning.Entities.Tache;
import com.example.pi_dev_4eme__poker_planning.Entities.User;
import com.example.pi_dev_4eme__poker_planning.Repositories.ITacheRepository;
import com.example.pi_dev_4eme__poker_planning.Repositories.ProjetRepositorie;
import com.example.pi_dev_4eme__poker_planning.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class TacheServices  implements ITacheServices {
    @Autowired
    ITacheRepository TRepository;
    @Autowired
    UserRepository userRepositories;
    @Autowired
    ProjetRepositorie pR;

    @Override
    public void addTache(Tache tache, User user, Projet projet) {
        // Set user and project objects directly in the tache
        tache.setUser(user);
        tache.setProjet(projet);

        // Set creation date for the task
        tache.setDateCreation(new Date());

        // Save the task
        TRepository.save(tache);
    }


    @Override
    public Tache UpdateTache(Tache tache, long idtache) {
        tache =TRepository.findTachesByIdTache(idtache);
        return TRepository.save(tache);
    }

    /*@Override
    public List<Tache> ShowTache() {
        return TRepository.findAll();
    }*/
    @Override
    public List<Tache> ShowTache() {
        return TRepository.findAll();
    }



    @Override
    public void DeleteTache(long idtache) {
        // Attempt to find the task by ID
        Tache tache = TRepository.findTachesByIdTache(idtache);

        if (tache != null) {
            // If the task is found, delete it from the repository
            TRepository.delete(tache);
        } else {
            // Handle the case where the task with the specified ID is not found
            throw new NoSuchElementException("Task with ID " + idtache + " not found");
        }
    }


    @Override
    public List<Tache> ShowTacheWithUserAndProjet() {
        return null;
    }


    @Override
    public long calculateRemainingDays(Tache tache) {
        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Convert the end date of the task (which is a Date object) to LocalDate
        Instant instant = tache.getDateFinTache().toInstant();
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        LocalDate endDate = zonedDateTime.toLocalDate();

        // Calculate the remaining days
        long remainingDays = ChronoUnit.DAYS.between(currentDate, endDate);

        return remainingDays;
    }

    public List<Tache> getTachesByUserId(long userId) {
        return TRepository.findByUserId(userId);
    }
    public Tache getTachesById(Long idTache) {
        return TRepository.findById(idTache).orElse(null);
    }

}
