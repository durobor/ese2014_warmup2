package org.sample.controller.service;

import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.SignupForm;
import org.sample.controller.pojos.TeamForm;
import org.sample.model.Address;
import org.sample.model.Team;
import org.sample.model.User;
import org.sample.model.dao.AddressDao;
import org.sample.model.dao.TeamDao;
import org.sample.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@Service
public class SampleServiceImpl implements SampleService {

    @Autowired    UserDao userDao;
    @Autowired    AddressDao addDao;
    @Autowired    TeamDao teamDao;
    
    public Iterable<Team> getTeams() {
    	return teamDao.findAll();
    }
    
    @Transactional
    public SignupForm saveFrom(SignupForm signupForm) throws InvalidUserException{

        String firstName = signupForm.getFirstName();

        if(!StringUtils.isEmpty(firstName) && "ESE".equalsIgnoreCase(firstName)) {
            throw new InvalidUserException("Sorry, ESE is not a valid name");   // throw exception
        }


        Address address = new Address();
        address.setStreet("TestStreet-foo");
        
        
        User user = new User();
        user.setFirstName(signupForm.getFirstName());
        user.setEmail(signupForm.getEmail());
        user.setLastName(signupForm.getLastName());
        user.setAddress(address);
        user.setTeam(signupForm.getTeam());
        
        user = userDao.save(user);   // save object to DB
        
        
        // Iterable<Address> addresses = addDao.findAll();  // find all 
        // Address anAddress = addDao.findOne((long)3); // find by ID
        
        
        signupForm.setId(user.getId());

        return signupForm;

    }

    @Transactional
	public TeamForm saveFrom(TeamForm teamForm) {

        String teamName = teamForm.getTeamName();

        if(!StringUtils.isEmpty(teamName) && "ESE".equalsIgnoreCase(teamName)) {
            throw new InvalidUserException("Sorry, ESE is not a valid name");   // throw exception
        }

        Team team = new Team();
        team.setTeamName(teamForm.getTeamName());
        team.setCreationDate();
        
        team = teamDao.save(team);   // save object to DB
        
        
        teamForm.setId(team.getId());

        return teamForm;
	}

	public User getUser(Long userId) {
		return userDao.findOne(userId);
	}
	
	public TeamDao getTeamDao () {
		return this.teamDao;
	}
}
