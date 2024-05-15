package com.example.pi_dev_4eme__poker_planning.Services;

import com.example.pi_dev_4eme__poker_planning.Entities.SprintBacklog;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface ISprintBacklogServices {
    SprintBacklog createSprintBacklog(SprintBacklog sprintBacklog);
    SprintBacklog updateSprintBacklog(Long id, SprintBacklog sprintBacklog) throws ChangeSetPersister.NotFoundException;
    List<SprintBacklog> getAllSprintBacklogs();
    SprintBacklog getSprintBacklogById(Long id) throws ChangeSetPersister.NotFoundException;
    void deleteSprintBacklog(Long id) throws ChangeSetPersister.NotFoundException;

    void assignSprintToSprintBacklog(Long sprintBacklogId, Long sprintId);
     void assignUserStoryToSprintBacklog(Long sprintBacklogId, Long userStoryId);
    public void unassignUserStoryFromSprintBacklog(Long userStoryId);


    void unassignSprintFromSprintBacklog(Long sprintBacklogId);
    List<SprintBacklog> getSprintBacklogsBySprintId(Long sprintId) ;
    int getSprintProgression(Long sprintId);


}