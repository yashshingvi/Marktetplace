package com.lti.service;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.entity.Bidder;
import com.lti.entity.Farmer;
import com.lti.entity.LoginCredentials;
import com.lti.exception.LoginException;
import com.lti.repository.LoginRepository;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginRepository loginRepository;

	public int login(LoginCredentials loginCredentials) {
		// TODO Auto-generated method stub
		try {
//			LoginCredentials lc = new LoginCredentials();
//			lc.setId("yash");
//			lc.setPassword("bbb");
//			lc.setType("FARMER");
//			loginRepository.save(lc);
			if (!loginRepository.isUserPresent(loginCredentials.getId(), loginCredentials.getType()))
				throw new LoginException("User not registered");
//			boolean auth = loginRepository.checkCredntials(loginCredentials);
			if (loginRepository.checkCredntials(loginCredentials)) {
				if (loginCredentials.getType().equals("FARMER")) {
					return ((Farmer) loginRepository.fetchByEmail(Farmer.class, loginCredentials.getId())).getId();

				} else if (loginCredentials.getType().equals("BIDDER")) {
					return ((Bidder) loginRepository.fetchByEmail(Bidder.class, loginCredentials.getId())).getId();

				} else if (loginCredentials.getType().equals("ADMIN")) {
//					Admin = (Farmer)loginRepository.fetchByEmail(Farmer.class, loginCredentials.getId());
					return 1;
				} else {
					throw new LoginException("Not My Type");
				}
			}

			throw new LoginException("Incorrect E-mail or Password");
		} catch (NoResultException e) {
			throw new LoginException("Incorrect E-mail or Password");
		}

	}

	@Override
	public String changePassword(LoginCredentials loginCredentials) {
		// method to send email
		try {
		loginRepository.save(loginCredentials);
		return "Password Changed";
		} catch (NoResultException e) {
			throw new LoginException("Incorrect E-mail or Password");
		}
		
	}

	
}
