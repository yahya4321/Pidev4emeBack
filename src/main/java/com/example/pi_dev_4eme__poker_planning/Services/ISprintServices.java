package com.example.pi_dev_4eme__poker_planning.Services;

import com.example.pi_dev_4eme__poker_planning.Entities.Sprint;

import java.util.List;
import java.util.Optional;

public interface ISprintServices {
    // void ajouterSprint (Sprint s);
    Sprint createSprint(Sprint sprint);
    List<Sprint> getAllSprints();
    Optional<Sprint> getSprintById(Long id);
    Sprint updateSprint(Long id, Sprint updatedSprint);
    void deleteSprintById(Long id);
}