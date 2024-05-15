package com.example.pi_dev_4eme__poker_planning.Controllers;

import com.example.pi_dev_4eme__poker_planning.Entities.Estimation;
import com.example.pi_dev_4eme__poker_planning.Services.EstimationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/Estimation")

public class EstimationControllers {
    @Autowired
    EstimationService estimationService;

    @PostMapping("/AddEstimation")
    public Estimation AddEstimation(@RequestBody Estimation estimation)
    {
        return estimationService.AddNewEstimation(estimation);
    }
    @PostMapping("/AddEstimationWithIteration/")
    @Transactional
    public void AddEstimationWithIteration(@RequestBody  Estimation estimation ,Principal connectedUser )
    {
        estimationService.AddEstimationAffectIteration(estimation,connectedUser);
    }
    @GetMapping("/GetEstimations/{id}")
    public List<Estimation> ShowEstimationsForOneIterations(@PathVariable("id") long id)
    {
        return estimationService.ShowEstimationsForOneIterations(id);
    }
    @GetMapping ("/GetEstimationsLastIteration/")
    public List<Estimation> ShowEstimationsForLastEstimation()
    {
        return estimationService.ShowEstimationsForLastIteration();
    }
    @PostMapping("/AffectUserToVote/{id}")
    public Estimation AddEstimationAffectIterationwithUserId(@RequestBody Estimation estimation,@PathVariable("id") long idUser)
    {
        return  estimationService.AddEstimationAffectIterationwithUserId(estimation,idUser);
    }


}