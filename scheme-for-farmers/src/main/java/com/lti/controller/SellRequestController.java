package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.PlaceSellRequest;
import com.lti.dto.SearchDto;
import com.lti.dto.SellRequestStatus;
import com.lti.dto.Status.StatusType;
import com.lti.entity.Bidder;
import com.lti.entity.SellRequest;
import com.lti.exception.BidderRegisterException;
import com.lti.exception.SellRequestException;
import com.lti.service.AdminService;
import com.lti.service.SellRequestService;


@RestController
@CrossOrigin
public class SellRequestController {


	@Autowired
	//@Qualifier(value="sellRequestService")
	private SellRequestService sellRequestService;
	
	@Autowired
	//@Qualifier(value="adminRequest")
	private AdminService adminRequest;

		
	@PostMapping("/register-sellreq")
	public @ResponseBody SellRequestStatus placeSellRequest(@RequestBody PlaceSellRequest s) {
		try {
			
		SellRequest registered = sellRequestService.placeSellRequestByValidFarmer(s);
		
		SellRequestStatus status = new SellRequestStatus ();
		status.setMessage("PLACED SUCCESSFULLY ! APPROVAL PENDING");
		status.setSellRequestId(registered.getId());
		status.setStatus(StatusType.SUCCESS);
		
		return status;
		}catch(SellRequestException e)
		{
			SellRequestStatus status = new SellRequestStatus ();
			status.setMessage(e.getMessage());
			status.setStatus(StatusType.FAILED);
			return status;
		}
	}
	
	@GetMapping("/getsellrequest")
	public List<PlaceSellRequest> getActiveSellRequest(){
		
		List<PlaceSellRequest> list = sellRequestService.getActiveSellRequest();
		return list;
	}
	
	@GetMapping("/view-profile-sellrequest")
	public SellRequest getSellRequestDetails(@RequestParam("id") int id) {

		SellRequest sellreq = sellRequestService.getSellReqDetails(id);
		return sellreq;

	}
	
	@PostMapping("/search-sellreq")
	public List<PlaceSellRequest> filterSellRequest(@RequestBody SearchDto search) 
	{
		List<PlaceSellRequest> list=sellRequestService.getActiveSellRequest();
		
		List<PlaceSellRequest> filteredList = sellRequestService.filterCrops(list,search);
		return filteredList;
	}
	
	
	 /*@GetMapping("/getAllrequest")
	public List<SellRequest> getAllActiveSellRequest(){
		
		List<SellRequest> list = sellRequestService.getActive();
		return list;
	} */
}
