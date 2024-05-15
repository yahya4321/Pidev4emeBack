package com.example.pi_dev_4eme__poker_planning.Entities;


import com.example.pi_dev_4eme__poker_planning.Configurations.GrantedAuthorityDeserializer;
import com.example.pi_dev_4eme__poker_planning.Configurations.GrantedAuthoritySerializer;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements UserDetails ,Serializable
{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idUser;
    private String Nom;
    private String Prenom;
    private String photo;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role rolee;
    @Enumerated(EnumType.STRING)
    private StatusUser Status;
    private String Tel;
    private String resetPasswordToken;
    // Constructor with idUser parameter
        public User(long idUser) {
            this.idUser = idUser;
        }
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore

    private List<Tache> taches;
    @JsonIgnore
    @OneToMany (mappedBy = "user",cascade = CascadeType.ALL)
    Set<Reunion> reunions;
    @JsonIgnore
    @ManyToMany (mappedBy = "users",cascade = CascadeType.ALL)
    Set<Reunion>reunionsAssiter;
    @JsonIgnore
    @OneToMany(mappedBy = "userReclamer",cascade = CascadeType.ALL)
    Set<Reclamation> reclamations;
   
    /*@ManyToMany (cascade = CascadeType.ALL)
    Set<Projet>Projets;*/

    @JsonIgnore
    @OneToOne(mappedBy = "user",cascade = CascadeType.REMOVE)
    private Estimation estimation;

    @JsonSerialize(contentUsing = GrantedAuthoritySerializer.class)
    @JsonDeserialize(contentUsing = GrantedAuthorityDeserializer.class)

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(rolee.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Transient
    private MultipartFile imageFile; // Transient field for image upload

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                this.photo = Base64.getEncoder().encodeToString(imageFile.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getPhotoData() {
        return photo;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}


