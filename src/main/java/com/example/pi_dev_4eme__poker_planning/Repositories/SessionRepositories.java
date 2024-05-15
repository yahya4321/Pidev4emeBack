package com.example.pi_dev_4eme__poker_planning.Repositories;


import com.example.pi_dev_4eme__poker_planning.Entities.Session;
import com.example.pi_dev_4eme__poker_planning.Entities.User;
import com.example.pi_dev_4eme__poker_planning.Entities.UserStory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepositories extends JpaRepository<Session, Integer> {
    Optional<Session> findByIdSession(Integer idsession);
    //List<Session> findAllByUsersIn(List<User> users);

    @Query("SELECT u FROM User u WHERE u.rolee = 'developer'")
    List<User> findDevelopers();

    @Query("SELECT s.LinkSession FROM Session s WHERE s.idSession = :idSession")
    String findLinkSessionByIdSession(@Param("idSession") Integer idSession);

    @Query("SELECT us FROM UserStory us WHERE us.projet.idProjet = :idProjet")
    List<UserStory> findByProjetId(@Param("idProjet") Integer idProjet);

}
