package com.example.pi_dev_4eme__poker_planning.Controllers;

import com.example.pi_dev_4eme__poker_planning.Entities.Sprint;
import com.example.pi_dev_4eme__poker_planning.Services.SprintBacklogServiceImpl;
import com.example.pi_dev_4eme__poker_planning.Services.SprintServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
//@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/api/sprints")


public class SprintController {
    @Autowired
    SprintServiceImpl sprintService;
    @Autowired
    private SprintBacklogServiceImpl sprintBacklogService;
   /* @PostMapping("/add-sprint")
    @ResponseBody
    public void ajousprint(@RequestBody Sprint sprint) {
        sprintService.ajouterSprint(sprint);
    }*/


    @PostMapping
    public ResponseEntity<Sprint> createSprint(@RequestBody Sprint sprint) {
        Sprint createdSprint = sprintService.createSprint(sprint);
        return new ResponseEntity<>(createdSprint, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Sprint>> getAllSprints() {
        List<Sprint> sprints = sprintService.getAllSprints();
        return new ResponseEntity<>(sprints, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sprint> getSprintById(@PathVariable Long id) {
        Optional<Sprint> sprint = sprintService.getSprintById(id);
        return sprint.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sprint> updateSprint(@PathVariable Long id, @RequestBody Sprint updatedSprint) {
        Sprint updatedSprintResult = sprintService.updateSprint(id, updatedSprint);
        return ResponseEntity.ok(updatedSprintResult);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSprint(@PathVariable Long id) {
        sprintService.deleteSprintById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{sprintId}/progression")
    public int getSprintProgression(@PathVariable Long sprintId) {
        return sprintBacklogService.getSprintProgression(sprintId);
    }

}