package com.lti.service;

import com.lti.entity.LoginCredentials;
import com.lti.entity.Status;

public interface LoginService {

	//login [1.Farmer approved or not    2.credentials check    1.Bidder approved or not    2.credentials check]
		//public Farmer/Bidder login(LoginCredentials c);
		
		public int login(LoginCredentials loginCredentials);

		public String changePassword(LoginCredentials loginCredentials);

		
		
	
	 //forgetpassword
	
	
	//reset password
}
