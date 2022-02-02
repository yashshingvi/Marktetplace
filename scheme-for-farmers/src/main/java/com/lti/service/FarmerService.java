package com.lti.service;

import java.util.List;

import com.lti.dto.HistoryDto;
import com.lti.entity.Farmer;
import com.lti.entity.LoginCredentials;
import com.lti.entity.SellRequest;

public interface FarmerService {
	
	//register [1.check whether farmer is already present  2.verify email address (sending OTP)  3.send email after registration ]
	public int registerFarmer(Farmer fm);
	
	//login [1.Farmer approved or not    2.credentials check]
//	public Farmer login(LoginCredentials c);
	
	//view-profile
	public Farmer getFarmerDetails(int farmerId);
	
	//edit-profile
	public Farmer editFarmerDetails(Farmer f);
	
	
	//place-sell-request [ 1.farmer approved  2. sell request-approved  3.date is greater than current date ]
	//public SellRequest placeSellRequest(SellRequest s);
	
	
	//view-market-place [1. farmer approved or not]
	public List<SellRequest> getMarketPlaceDetails(int farmerId);
	
	public List<HistoryDto> getSellHistory(int id);
	

	public void updateCertificate(int id, String newFileName);
	
	public Farmer get(int id) ;
	

}
