package com.example.pi_dev_4eme__poker_planning.Services;

import com.example.pi_dev_4eme__poker_planning.Entities.Session;
import com.example.pi_dev_4eme__poker_planning.Entities.User;
import com.example.pi_dev_4eme__poker_planning.Entities.UserStory;

import java.util.List;

public interface ISessionRepositories {
    public Session addSession(Session s);

    public void removeSession(Integer idSession);

    public Session modifiySession(Integer idSession, Session updateSession );

    public List<Session> retrieveAllSession();
    public List<User> retrieveAlldevolper();

    public Session retrieveSessionById(Integer idSession );

    public String getLink(Integer idSession);

   // public List<User> getDeveloperUsersForSession(Integer idSession) ;

    public List<UserStory> getUserStoriesByProjetId (Integer idProjet) ;


}
