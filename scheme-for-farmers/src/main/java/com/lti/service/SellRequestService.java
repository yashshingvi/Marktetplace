package com.lti.service;

import java.util.List;

import com.lti.dto.PlaceSellRequest;
import com.lti.dto.SearchDto;
import com.lti.entity.SellRequest;


public interface SellRequestService {

	// display sellRequest which are active (which is not expired)
	public List<PlaceSellRequest> getActiveSellRequest();

	// place-sell-request [ 1.farmer approved 2. sell request-approved 3.date is greater than current date ]
	public SellRequest placeSellRequestByValidFarmer(PlaceSellRequest s);
	
	 //public List<SellRequest> getActive();
	public SellRequest getSellReqDetails(int id);
	
	
	public List<PlaceSellRequest> filterCrops(List<PlaceSellRequest> list, SearchDto search);
}
