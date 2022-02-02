package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.BidSupport;
import com.lti.dto.Status;
import com.lti.dto.Status.StatusType;
import com.lti.entity.BidInfo;
import com.lti.exception.BidServiceException;
import com.lti.service.BidService;

@RestController
@CrossOrigin
public class BidController {
	
	@Autowired
	private BidService bidService;
	
	@PostMapping("/placing-bid")  
	public @ResponseBody Status toPlaceBid(@RequestBody BidSupport bidSupport) {
		Status status=new Status();
		try {
			bidService.placeBid(bidSupport);
			
			status.setStatus(StatusType.SUCCESS);
			status.setMessage("Placed bid Successful");
		}
		catch (BidServiceException e){
			status.setStatus(StatusType.FAILED);
			status.setMessage(e.getMessage());
		}
		return status;
	}
	
	@GetMapping("/max-bid")
	public float toMaxBid(@RequestParam("id") int id)
	{
		return bidService.maximumBid(id);
		
	}
	
	@GetMapping("/all-bids")
	public List<BidInfo> toAllBid(@RequestParam("id") int id)
	{
		return bidService.fetchAllBid(id);
		
	}
	
	@GetMapping("/all-sellbids")
	public List<BidInfo> toAllSellBid(@RequestParam("id") int id)
	{
		return bidService.fetchAllSellBid(id);
		
	}

}
