package com.lti.service;

import java.util.List;

import com.lti.dto.BidSupport;
import com.lti.entity.BidInfo;
import com.lti.entity.Bidder;
import com.lti.entity.SellRequest;

public interface BidService {

	public void placeBid(BidSupport bidSupport);
	
	public float maximumBid(int id);
	
	public List<BidInfo>fetchAllBid(int id);

	public List<BidInfo> fetchAllSellBid(int id);
	
}
