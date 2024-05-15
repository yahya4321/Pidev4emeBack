package com.example.pi_dev_4eme__poker_planning.Services;

import com.example.pi_dev_4eme__poker_planning.Entities.Sprint;
import com.example.pi_dev_4eme__poker_planning.Repositories.SprintRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class SprintServiceImpl implements ISprintServices {
    @Autowired
    private SprintRepository sprintRepository;


    public Sprint createSprint(Sprint sprint) {
        return sprintRepository.save(sprint);
    }


    public List<Sprint> getAllSprints() {
        return sprintRepository.findAll();
    }


    public Optional<Sprint> getSprintById(Long id) {
        return sprintRepository.findById(id);
    }

    public Sprint updateSprint(Long id, Sprint updatedSprint) {
        Sprint existingSprint = sprintRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sprint non trouvé avec l'ID : " + id));

        // Mettez à jour les champs nécessaires
        existingSprint.setNomSprint(updatedSprint.getNomSprint());
        existingSprint.setObjectifSprint(updatedSprint.getObjectifSprint());
        existingSprint.setDateDebutSprint(updatedSprint.getDateDebutSprint());
        existingSprint.setDateFinSprint(updatedSprint.getDateFinSprint());
        existingSprint.setEtatSprint(updatedSprint.getEtatSprint());

        // Vous pouvez également mettre à jour les relations, le cas échéant

        // Enregistrez les modifications dans la base de données
        return sprintRepository.save(existingSprint);
    }

    public void deleteSprintById(Long id) {
        sprintRepository.deleteById(id);

    }
}