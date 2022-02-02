package com.lti.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.dto.BidSupport;
import com.lti.entity.BidInfo;
import com.lti.entity.Bidder;
import com.lti.entity.SellRequest;
import com.lti.entity.Status;
import com.lti.exception.BidServiceException;
import com.lti.exception.SellRequestException;
import com.lti.repository.BidInfoRepository;
import com.lti.repository.BidderRepository;
import com.lti.repository.SellRequestRepository;

@Service
@Transactional
public class BidServiceImpl  implements BidService {
	
	@Autowired
	private BidInfoRepository bidInfoRepository;
	
	@Autowired
	private BidderRepository bidderRepository;
	
	@Autowired
	private SellRequestRepository sellRequestRepository;
	
	public void placeBid(BidSupport bidSupport) {
		BidInfo bi = new BidInfo();
		
		int id = bidSupport.getBidderId();
		Bidder br = bidderRepository.fetch(Bidder.class, id);

//		if (br.getStatus() == Status.PENDING || br.getStatus() == Status.REJECTED)
//			throw new BidServiceException("Your Id is Not Approved By Admin");
		
		bi.setBidAmount(bidSupport.getBidAmount());
		
		if(bidInfoRepository.isBidPresent(bidSupport.getSellRequestId())) {
			if(bidSupport.getBidAmount() < bidInfoRepository.getMaximumBid(bidSupport.getSellRequestId())) {
				throw new BidServiceException("Bid amount should be greater than " + bidInfoRepository.getMaximumBid(bidSupport.getSellRequestId()));
			}
			
			else {
				bi.setBidder(bidInfoRepository.fetch(Bidder.class, bidSupport.getBidderId()));
				bi.setSellRequest(bidInfoRepository.fetch(SellRequest.class, bidSupport.getSellRequestId()));
				bidInfoRepository.save(bi);
			}
		}
		
		else {
			if(bidSupport.getBidAmount() < sellRequestRepository.getBasePrice(bidSupport.getSellRequestId()))
				throw new BidServiceException("Bid amount should be greater than base price " + sellRequestRepository.getBasePrice(bidSupport.getSellRequestId()));
			
			else {
				bi.setBidder(bidInfoRepository.fetch(Bidder.class, bidSupport.getBidderId()));
				bi.setSellRequest(bidInfoRepository.fetch(SellRequest.class, bidSupport.getSellRequestId()));
				bidInfoRepository.save(bi);
			}
		}
			
	}

	public float maximumBid(int id) {
		float maxbid=0f;
		if(bidInfoRepository.isBidPresent(id))
		 maxbid = bidInfoRepository.getMaximumBid(id);	
		
		
		return maxbid;
//		try {
// 		float maxbid = bidInfoRepository.getMaximumBid(id);
// 		return maxbid;
//		}
//		catch(NullPointerException e) {
//			return 0;
//		}
	}

	public List<BidInfo> fetchAllBid(int id) {
		List<BidInfo> list = bidInfoRepository.getFetchAllBid(id);
		return list;
	}
	
	public List<BidInfo> fetchAllSellBid(int id) {
		List<BidInfo> list = bidInfoRepository.getFetchAllSellBid(id);
		return list;
	}

}
