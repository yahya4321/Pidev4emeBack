package com.example.pi_dev_4eme__poker_planning.Controllers;


import com.example.pi_dev_4eme__poker_planning.Entities.Role;
import com.example.pi_dev_4eme__poker_planning.Entities.StatusUser;
import com.example.pi_dev_4eme__poker_planning.Entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;





    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public class AuthenticationResponse {
        String token;
        private long idUser;
        private String nom;
        private String prenom;
        private String photo;
        private String email;
        private String password;
        private Role rolee;
        private StatusUser status;
        private String tel;


    }


