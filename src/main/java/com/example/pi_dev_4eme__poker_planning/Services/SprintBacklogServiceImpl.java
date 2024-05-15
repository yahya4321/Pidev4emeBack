package com.example.pi_dev_4eme__poker_planning.Services;

import com.example.pi_dev_4eme__poker_planning.Entities.Sprint;
import com.example.pi_dev_4eme__poker_planning.Entities.SprintBacklog;
import com.example.pi_dev_4eme__poker_planning.Entities.UserStory;
import com.example.pi_dev_4eme__poker_planning.Repositories.SprintBacklogRepository;
import com.example.pi_dev_4eme__poker_planning.Repositories.SprintRepository;
import com.example.pi_dev_4eme__poker_planning.Repositories.UserStoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class SprintBacklogServiceImpl implements ISprintBacklogServices {

    @Autowired
    SprintBacklogRepository sprintBacklogRepository;
    @Autowired
    UserStoryRepository userStoryRepository;
    @Autowired
    SprintRepository sprintRepository;



    @Override
    public SprintBacklog createSprintBacklog(SprintBacklog sprintBacklog) {
        return sprintBacklogRepository.save(sprintBacklog);
    }

    @Override
    public SprintBacklog updateSprintBacklog(Long id, SprintBacklog sprintBacklog) throws ChangeSetPersister.NotFoundException {
        if (sprintBacklogRepository.existsById(id)) {
            sprintBacklog.setIdSprintBacklog(id);
            return sprintBacklogRepository.save(sprintBacklog);
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Override
    public List<SprintBacklog> getAllSprintBacklogs() {
        return sprintBacklogRepository.findAll();
    }

    @Override
    public SprintBacklog getSprintBacklogById(Long id) throws ChangeSetPersister.NotFoundException {
        return sprintBacklogRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }

    @Override
    public void deleteSprintBacklog(Long id) throws ChangeSetPersister.NotFoundException {
        if (sprintBacklogRepository.existsById(id)) {
            sprintBacklogRepository.deleteById(id);
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }


    @Override
    public void assignSprintToSprintBacklog(Long sprintBacklogId, Long sprintId) {
        Optional<SprintBacklog> optionalSprintBacklog = sprintBacklogRepository.findById(sprintBacklogId);
        Optional<Sprint> optionalSprint = sprintRepository.findById(sprintId);

        if (optionalSprintBacklog.isPresent() && optionalSprint.isPresent()) {
            SprintBacklog sprintBacklog = optionalSprintBacklog.get();
            Sprint sprint = optionalSprint.get();

            sprintBacklog.setSprint(sprint);
            sprintBacklogRepository.save(sprintBacklog);
        } else {
            // Gérer le cas où SprintBacklog ou Sprint n'est pas trouvé
            throw new SprintBacklogNotFoundException("SprintBacklog ou Sprint non trouvé");
        }
    }

    @Transactional
    @Override
    public void unassignSprintFromSprintBacklog(Long sprintBacklogId) {
        Optional<SprintBacklog> optionalSprintBacklog = sprintBacklogRepository.findById(sprintBacklogId);

        if (optionalSprintBacklog.isPresent()) {
            SprintBacklog sprintBacklog = optionalSprintBacklog.get();
            sprintBacklog.setSprint(null);  // Désaffecter en mettant le Sprint à null
            sprintBacklogRepository.save(sprintBacklog);
        } else {
            throw new SprintBacklogNotFoundException("SprintBacklog non trouvé");
        }
    }
@Override
    public void assignUserStoryToSprintBacklog(Long sprintBacklogId, Long userStoryId)
            throws SprintBacklogNotFoundException, UserStoryNotFoundException {
        SprintBacklog sprintBacklog = sprintBacklogRepository.findById(sprintBacklogId)
                .orElseThrow(() -> new SprintBacklogNotFoundException("Sprint Backlog not found with ID: " + sprintBacklogId));

        UserStory userStory = userStoryRepository.findById(userStoryId)
                .orElseThrow(() -> new UserStoryNotFoundException("User Story not found with ID: " + userStoryId));


    userStory.setSprintBacklog(sprintBacklog); // Affectation du sprint backlog à la user story
    userStoryRepository.save(userStory);

    }


    @Override
    public void unassignUserStoryFromSprintBacklog(Long userStoryId)
            throws UserStoryNotFoundException {
        UserStory userStory = userStoryRepository.findById(userStoryId)
                .orElseThrow(() -> new UserStoryNotFoundException("User Story not found with ID: " + userStoryId));

        userStory.setSprintBacklog(null); // Désaffectation du sprint backlog de la user story
        userStoryRepository.save(userStory);
    }

    @Override
    public List<SprintBacklog> getSprintBacklogsBySprintId(Long sprintId) {

        return sprintBacklogRepository.findBySprintIdSprint(sprintId);
    }
    @Override
    public int getSprintProgression(Long sprintId) {
        long totalSprintBacklogs = sprintBacklogRepository.countBySprintIdSprint(sprintId);
        long sprintBacklogsTermines = sprintBacklogRepository.countBySprintIdSprintAndAndEstTermine(sprintId, true);

        if (totalSprintBacklogs > 0) {
            return (int) ((sprintBacklogsTermines * 100) / totalSprintBacklogs);
        } else {
            return 0;
        }
    }
    public List<SprintBacklog> filterSprintBacklogsByDate(Date startDate, Date endDate) {
        if (startDate != null && endDate != null) {
            return sprintBacklogRepository.findByDateDebutAfterAndDateFinBefore(startDate, endDate);
        } else if (startDate != null) {
            return sprintBacklogRepository.findByDateDebutAfter(startDate);
        } else if (endDate != null) {
            return sprintBacklogRepository.findByDateFinBefore(endDate);
        } else {
            return sprintBacklogRepository.findAll();
        }
    }

}