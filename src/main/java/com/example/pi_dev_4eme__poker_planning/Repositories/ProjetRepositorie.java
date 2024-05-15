package com.example.pi_dev_4eme__poker_planning.Repositories;

import com.example.pi_dev_4eme__poker_planning.Entities.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
    public interface ProjetRepositorie extends JpaRepository<Projet,Long> {
        //List <Projet> findAllByIdProjetAndNom_Projet(int IdProjet,String Nom_Projet);
        //Projet findByNom_Projet(String Nom_Projet);
        Projet findByIdProjet(Long IdProjet);
    }

