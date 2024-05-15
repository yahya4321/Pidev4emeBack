package com.example.pi_dev_4eme__poker_planning.Services;

import com.example.pi_dev_4eme__poker_planning.Entities.Estimation;
import com.example.pi_dev_4eme__poker_planning.Entities.Iteration;
import com.example.pi_dev_4eme__poker_planning.Repositories.IterationRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IterationServices implements IiterationServices{
    @Autowired
    IterationRepositories iiterationrepositories;

    @Override
    public Iteration AddIteration(Iteration iteration) {


        return iiterationrepositories.save(iteration);
    }

    @Override
    public Iteration UpdateIteration(Iteration iteration, long idIteration) {
        Iteration existiteration=iiterationrepositories.findIterationByIdIteration(idIteration);
        existiteration.setResultat(iteration.getResultat());
        existiteration.setDate_IterationDebut(iteration.getDate_IterationDebut());
        existiteration.setDate_IterationFin(iteration.getDate_IterationFin());
        return  iiterationrepositories.save(existiteration);
    }

    @Override
    public List<Iteration> ShowIteration() {
        return  iiterationrepositories.findAll();
    }

    @Override
    public void DeleteIteration( long idIteration) {
        Iteration iteration= iiterationrepositories.findIterationByIdIteration(idIteration);
        iiterationrepositories.delete(iteration);
    }

    @Override
    public Iteration getIterationById(long idIteration) {
        Iteration iteration= iiterationrepositories.findIterationByIdIteration(idIteration);
        return iteration;
    }

}