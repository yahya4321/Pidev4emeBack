package com.example.pi_dev_4eme__poker_planning.Controllers;


import com.example.pi_dev_4eme__poker_planning.Entities.Iteration;
import com.example.pi_dev_4eme__poker_planning.Services.IterationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Iteration")
public class IterationControllers {
    @Autowired
    IterationServices iterationServices;
    @PostMapping("/AddIteration/")
    public Iteration AddIteration( @RequestBody  Iteration Iteration)
    {
        return iterationServices.AddIteration(Iteration);
    }
    @GetMapping("/GetIteration/")
    List<Iteration> ShowIteration()
    {
        return iterationServices.ShowIteration();
    }
    @PutMapping("/UpdateIteration/{id}")
    Iteration UpdateIteration(@RequestBody Iteration iteration, @PathVariable("id") long idIteration) {
        return iterationServices.UpdateIteration(iteration, idIteration);
    }
    @DeleteMapping("/DeleteIteration/{id}")
    void DeleteIteration(  @PathVariable("id") long idIteration)
    {
        iterationServices.DeleteIteration( idIteration);

    }
    @GetMapping("/getIteration/{id}")
    public Iteration getIterationById(@PathVariable long id) {
        return iterationServices.getIterationById(id);
    }
}