package com.example.pi_dev_4eme__poker_planning.Services;

import com.example.pi_dev_4eme__poker_planning.Entities.UserStory;
import com.example.pi_dev_4eme__poker_planning.Repositories.UserStoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserStoryServiceImpl implements IUserStoryService {
    @Autowired
    UserStoryRepository userStoryRepository;

    @Override
    public List<UserStory> getAllUserStorys() {
        List<UserStory> userstorys = userStoryRepository.findAll();
        return userstorys;
    }

    @Override
    public UserStory saveUserStory(UserStory userStory) {

        return userStoryRepository.save(userStory);
    }

    @Override
    public void deleteUserStory(Long IdUserStory) {
        userStoryRepository.deleteById(IdUserStory);
    }


    @Override
    public UserStory updateUserStory(Long IdUserStory, UserStory userStory) {
        Optional<UserStory> existingUserStoryOptional = userStoryRepository.findById(IdUserStory);
        if (existingUserStoryOptional.isPresent()) {
            UserStory existingUserStory = existingUserStoryOptional.get();
            // Mettre à jour les champs nécessaires de l'user story existante
            existingUserStory.setTitre_US(userStory.getTitre_US());
            existingUserStory.setDescription_US(userStory.getDescription_US());
            existingUserStory.setStatut_US(userStory.getStatut_US());
            existingUserStory.setVelocite_US(userStory.getVelocite_US());
            // Enregistrer les modifications dans la base de données
            return userStoryRepository.save(existingUserStory);
        } else {
            // Gérer le cas où l'user story n'est pas trouvée
            return null;
        }


    }
    //details projet pour userstory specifique

}
