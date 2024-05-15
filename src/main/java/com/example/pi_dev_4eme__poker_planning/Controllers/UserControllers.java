package com.example.pi_dev_4eme__poker_planning.Controllers;


import com.example.pi_dev_4eme__poker_planning.Entities.Role;
import com.example.pi_dev_4eme__poker_planning.Entities.Tache;
import com.example.pi_dev_4eme__poker_planning.Entities.User;
import com.example.pi_dev_4eme__poker_planning.Services.TacheServices;
import com.example.pi_dev_4eme__poker_planning.Services.UserServices;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/User")
public class UserControllers {
    @Autowired
    UserServices Uservice;

    @GetMapping("/GetUser/{userId}")
    User getUserById(@PathVariable Long userId) {
        return Uservice.getUserById(userId);
    }
    @GetMapping("/GetUsers/")
    List<User> ShowAllUsers()
    {
        return Uservice.ShowAllUsers();
    }
    @PutMapping("/UpdateUser/{userId}")
    public User updateUserById(@PathVariable Long userId, @RequestBody User updatedUser) {
        return Uservice.updateUserById(userId, updatedUser);
    }

    @PutMapping("/ActivateUser/{userId}")
    public void activateUserById(@PathVariable Long userId) {
        Uservice.activateUserById(userId);
    }

    @PutMapping("/DeactivateUser/{userId}")
    public void deactivateUserById(@PathVariable Long userId) {
        Uservice.deactivateUserById(userId);
    }
    @GetMapping("/scrum-masters")
    public int countScrumMasters() {
        return Uservice.countScrumMasters();
    }

    @GetMapping("/developers")
    public int countDevelopers() {
        return Uservice.countDevelopers();
    }

    @GetMapping("/product-owners")
    public int countProductOwners() {
        return Uservice.countProductOwners();
    }
    @GetMapping("/active-users")
    public int countActiveUsers() {
        return Uservice.countActiveUsers();
    }

    @GetMapping("/inactive-users")
    public int countInactiveUsers() {
        return Uservice.countInactiveUsers();
    }
    @PatchMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request, Principal connectedUser) {
        Uservice.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/getNomUser")
    public List<String> getAllTitreUser(){
        return Uservice.getAllTitreUser();
    }

    //upload image
    @PostMapping("/upload/{idUser}")
    public User handleFileUpload(@RequestParam("photo") MultipartFile file, @PathVariable("idUser") long idUser) {

        return Uservice.storeFile(file,idUser);
    }
    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("fileName") String fileName) {
        Resource resource = Uservice.loadFileAsResource(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(resource);
    }

}
