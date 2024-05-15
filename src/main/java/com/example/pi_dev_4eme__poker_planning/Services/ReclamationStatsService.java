package com.example.pi_dev_4eme__poker_planning.Services;

import com.example.pi_dev_4eme__poker_planning.Entities.Reclamation;
import com.example.pi_dev_4eme__poker_planning.Repositories.ReclamationRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

public class ReclamationStatsService {
    @Autowired
    ReclamationRepositories reclamationRepositories;
    public List<Object[]> getReclamationCountByReunion() {
        return reclamationRepositories.getReclamationCountByReunion();
    }

}