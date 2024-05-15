package com.example.pi_dev_4eme__poker_planning.Repositories;

import com.example.pi_dev_4eme__poker_planning.Entities.Role;
import com.example.pi_dev_4eme__poker_planning.Entities.StatusUser;
import com.example.pi_dev_4eme__poker_planning.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

        Optional<User> findByEmail(String email);
        @Query("SELECT COUNT(u) FROM User u WHERE u.rolee = :role")
        int countUsersByRole(@Param("role") Role role);


        @Query("SELECT COUNT(u) FROM User u WHERE u.Status = :status")
        int countUsersByStatus(@Param("status") StatusUser status);
        @Query("SELECT u FROM User u WHERE u.Nom = :userName")
        User findUsersByNomUser(String userName);
        @Query("SELECT r.Nom FROM User r")
        List<String> findAllNOM();
        User findUserByIdUser(long idUser);
        Optional<User> findByResetPasswordToken(String token);
        @Query("SELECT u FROM User u WHERE u.rolee = :role")
        List<User> findByRole(@Param("role") Role role);
}
