package com.example.pi_dev_4eme__poker_planning.Services;

import com.example.pi_dev_4eme__poker_planning.Entities.Session;
import com.example.pi_dev_4eme__poker_planning.Entities.StatutDeSession;
import com.example.pi_dev_4eme__poker_planning.Entities.User;
import com.example.pi_dev_4eme__poker_planning.Entities.UserStory;
import com.example.pi_dev_4eme__poker_planning.Repositories.SessionRepositories;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class SessionServices implements ISessionRepositories {

    @Autowired
    SessionRepositories sessionRepositories;

   /* @Autowired
    TaskScheduler taskScheduler;*/

    @Override
    public Session addSession(Session s) {
        String CodeSession = UUID.randomUUID().toString().substring(0, 6);
        s.setCodeSession(CodeSession);

        Session savedSession = sessionRepositories.save(s);
        /*taskScheduler.schedule(() -> {
            savedSession.setStatut(StatutDeSession.Terminer);
        }, Instant.now().plusSeconds(60));*/

        String LinkSession = generateJoinSessionLink(savedSession.getCodeSession());
        savedSession.setLinkSession(LinkSession);
        sessionRepositories.save(savedSession);

        return savedSession;
    }

    private String generateJoinSessionLink(String codeSession) {
        return "http://localhost:4200/Session/rejoindre/" + codeSession;
    }

    /*@Override
    public String generateLink(){
        String Link = "http://localhost:4200/Session/rejoindre"+;
        return Link;
    }*/

    @Override
    public void removeSession(Integer idSession) {
        sessionRepositories.deleteById(idSession);
    }

    @Override
    public Session modifiySession(Integer idSession, Session updateSession) {
        Optional<Session> optUpdate = sessionRepositories.findByIdSession(idSession);
        Session SessionExist = null;
        if (optUpdate.isPresent()) {
            SessionExist = optUpdate.get();

            SessionExist.setNomSession(updateSession.getNomSession());
            SessionExist.setDescription(updateSession.getDescription());
            SessionExist.setDateEtHeureDebut(updateSession.getDateEtHeureDebut());
            SessionExist.setDateEtHeureFin(updateSession.getDateEtHeureFin());
            SessionExist.setStatut(updateSession.getStatut());
            return sessionRepositories.save(SessionExist);
        } else {
            return null;
        }

    }

    @Override
    public List<Session> retrieveAllSession() {
        List<Session> listSession = sessionRepositories.findAll();
        for (Session S : listSession) {
            log.info("Session: " + S);
        }
        return listSession;
    }

    @Override
    public List<User> retrieveAlldevolper() {
        List<User> listUser = sessionRepositories.findDevelopers();
        for (User U : listUser) {
            log.info("User" + U);
        }
        return listUser;
    }

    @Override
    public Session retrieveSessionById(Integer idsession) {

        Optional<Session> opt = sessionRepositories.findByIdSession(idsession);
        if (opt.isPresent()) {
            return opt.get();
        } else {
            return null;
        }
    }

    @Override
    public String getLink(Integer idSession) {
        String link=sessionRepositories.findLinkSessionByIdSession(idSession);
        return link;
    }

    @Override
    public List<UserStory> getUserStoriesByProjetId(Integer idProjet) {
        return sessionRepositories.findByProjetId(idProjet);
    }


}






