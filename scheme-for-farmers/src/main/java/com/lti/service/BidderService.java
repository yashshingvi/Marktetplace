package com.lti.service;

import java.util.List;

import com.lti.dto.BidderHistoryDto;
import com.lti.dto.PlaceBidByBidder;
import com.lti.entity.BidInfo;
import com.lti.entity.Bidder;
import com.lti.entity.LoginCredentials;
import com.lti.entity.SellRequest;

public interface BidderService {
	
	//register [1.check whether bidder is already present  2.verify email address (sending OTP)  3.send email after registration ]
		public Bidder registerBidder(Bidder b);
		
		
		//view-profile
		public Bidder getBidderDetails(int bidderId);
		
		//edit-profile
		
		
		public List<SellRequest> getAvailableBids();
		
		public Bidder editProfileDetails(Bidder b);
		
		public BidInfo placeBid(PlaceBidByBidder placeBid);
		
		public List<BidderHistoryDto> getHistory(int id);


}
