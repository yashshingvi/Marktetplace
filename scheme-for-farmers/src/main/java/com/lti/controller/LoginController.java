package com.lti.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.LoginStatus;
import com.lti.dto.Status.StatusType;
import com.lti.entity.LoginCredentials;
import com.lti.exception.LoginException;
import com.lti.service.LoginService;

@RestController
@CrossOrigin
public class LoginController {

	@Autowired
	private LoginService loginService;

	@PostMapping("/login1")
	public @ResponseBody LoginStatus loginStatus(@RequestBody LoginCredentials loginCredentials) {
		try {

			LoginStatus status = new LoginStatus();
			status.setId((loginService.login(loginCredentials)));

//			if (loginCredentials.getType().equals("FARMER")) {
//				status.setId(((Farmer) loginService.login(loginCredentials)).getId());
//
//			} else if (loginCredentials.getType().equals("BIDDER")) {
//				status.setId(((Bidder) loginService.login(loginCredentials)).getId());
//
//			}
//			else if(loginCredentials.getType().equals("ADMIN")){
////				status.setReturnObject(loginService.login(loginCredentials));
//			}
//			else {
//				throw new 
//			}
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Login Successful!");

			return status;
		} catch (LoginException e) {

			LoginStatus status = new LoginStatus();
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}
	}

	@PostMapping("/set-password")//also can be used for changing pwd
	public @ResponseBody LoginStatus forgotPassword(@RequestBody LoginCredentials loginCredentials) {
		LoginStatus status = new LoginStatus();
		try {
			status.setMessage(loginService.changePassword( loginCredentials));
			status.setStatus(StatusType.SUCCESS);
			return status;
		} catch (LoginException e) {
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
			return status;
		}

	}
}
