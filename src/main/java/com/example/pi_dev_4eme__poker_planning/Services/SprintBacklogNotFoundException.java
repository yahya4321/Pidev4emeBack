package com.example.pi_dev_4eme__poker_planning.Services;

public class SprintBacklogNotFoundException extends RuntimeException {
    public SprintBacklogNotFoundException(String id) {
        super("SprintBacklog avec l'ID " + id + " non trouvé.");
    }
}