package org.sample.controller.service;

import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.SignupForm;
import org.sample.controller.pojos.TeamForm;
import org.sample.model.Team;
import org.sample.model.User;
import org.sample.model.dao.TeamDao;

public interface SampleService {

    public SignupForm saveFrom(SignupForm signupForm) throws InvalidUserException;
    
    public TeamForm saveFrom(TeamForm teamForm);
    
    public Iterable<Team> getTeams();

	public User getUser(Long userId);
	
	public TeamDao getTeamDao();

}
