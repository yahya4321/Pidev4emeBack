package com.example.pi_dev_4eme__poker_planning.Services;

import com.example.pi_dev_4eme__poker_planning.Entities.Iteration;

import java.util.List;

public interface IiterationServices {
    Iteration AddIteration(Iteration iteration);
    Iteration UpdateIteration(Iteration iteration, long idIteration);
    List<Iteration> ShowIteration();
    void DeleteIteration(long idIteration);
    public Iteration getIterationById(long idIteration);
}