package com.lti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.BidderHistoryDto;
import com.lti.dto.BidderRegisterStatus;
import com.lti.dto.PlaceBidByBidder;
import com.lti.dto.Status;
import com.lti.dto.Status.StatusType;
import com.lti.entity.BidInfo;
import com.lti.entity.Bidder;
import com.lti.exception.BidderRegisterException;
import com.lti.service.AdminService;
import com.lti.service.BidderService;

@RestController
@CrossOrigin
public class BidderController {

	@Autowired
	private BidderService bidderService;

	@Autowired
	private AdminService adminRequest;

	@GetMapping("/view-profile-bidder")
	public Bidder getBidderDetails(@RequestParam("id") int id) throws BidderRegisterException {

		Bidder bidder = bidderService.getBidderDetails(id);
		return bidder;

	}

	@PostMapping("/register-bidder")
	public Status registration(@RequestBody Bidder b) {

		BidderRegisterStatus status = new BidderRegisterStatus();
		try {
			Bidder registeredBidder = bidderService.registerBidder(b);

			status.setMessage("Register Successfully !");
			status.setStatus(StatusType.SUCCESS);
			status.setBidderId(registeredBidder.getId());
			status.setBidderName(registeredBidder.getName());
			return status;
		} catch (BidderRegisterException e) {
			status.setMessage(e.getMessage());
			status.setStatus(StatusType.FAILED);
			return status;

		}
	}

	@PostMapping("/edit-bidder")
	public Status editBidderDetails(@RequestBody Bidder b) {

		BidderRegisterStatus status = new BidderRegisterStatus();

		try {
			Bidder updatedBidder = bidderService.editProfileDetails(b);
			status.setMessage("Updated Successfully !");
			status.setStatus(StatusType.SUCCESS);
			status.setBidderId(updatedBidder.getId());
			status.setBidderName(updatedBidder.getName());
			return status;
		} catch (BidderRegisterException e) {
			status.setMessage("Something went wrong ! Try Again");
			status.setStatus(StatusType.FAILED);
			return status;
		}

	}

	@PostMapping("/place-bid")
	public BidInfo PlaceBid(@RequestBody PlaceBidByBidder placeBid) {
		
		BidInfo bidInfo = bidderService.placeBid(placeBid);
		return null;
	}
	
	@GetMapping("/bidder-history")
	public List<BidderHistoryDto> getBidderHistory(@RequestParam("id") int id)
	{
		return bidderService.getHistory(id);
	}
	

}
