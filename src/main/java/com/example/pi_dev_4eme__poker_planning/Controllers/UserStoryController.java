package com.example.pi_dev_4eme__poker_planning.Controllers;

import com.example.pi_dev_4eme__poker_planning.Entities.UserStory;
import com.example.pi_dev_4eme__poker_planning.Services.IUserStoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userstory")

public class UserStoryController {

    @Autowired
    IUserStoryService userStoryService;
    /*@PostMapping("/addUserstory")
    UserStory addUserStory(@RequestBody UserStory userStory){
        return userStoryService.addUserStory(userStory);
    }*/
    @GetMapping("/getUserStory")
    public List<UserStory> getAllUserStories() {
        return userStoryService.getAllUserStorys();
    }



    @PostMapping("/createUserStory")
    public UserStory createOrUpdateUserStory(@RequestBody UserStory userStory) {
        return userStoryService.saveUserStory(userStory);
    }

    @DeleteMapping("/deleteUserStory/{IdUserStory}")
    public void deleteUserStory(@PathVariable Long IdUserStory) {
        userStoryService.deleteUserStory(IdUserStory);
    }
    @PutMapping("/updateUserStory/{IdUserStory}")
    public UserStory updateUserStory(@PathVariable Long IdUserStory, @RequestBody UserStory userStory) {
        return userStoryService.updateUserStory(IdUserStory, userStory);
    }



}
