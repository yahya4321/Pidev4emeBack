package com.example.pi_dev_4eme__poker_planning.Repositories;

import com.example.pi_dev_4eme__poker_planning.Entities.Sprint;
import com.example.pi_dev_4eme__poker_planning.Entities.TacheTechnique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TacheTechniqueRepository extends JpaRepository<TacheTechnique, Long> {
    List<TacheTechnique> findByNomTacheTechniqueContainingOrDescriptionTacheTechniqueContaining(String nom, String description);
    List<TacheTechnique> findAllByDevAffecteeIdUser(Long devAfffecteeId);

    List<TacheTechnique> findByDevAffecteeIdUser(Long devId);





}