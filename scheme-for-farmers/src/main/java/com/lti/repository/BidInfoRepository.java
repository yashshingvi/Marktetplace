package com.lti.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lti.entity.BidInfo;
import com.lti.entity.SellRequest;

@Repository
public class BidInfoRepository extends GenericRepository {

	public float getMaximumBid(int id) {
		
		return(float)
				entityManager
				.createQuery("select max(b.bidAmount) from BidInfo b where b.sellRequest.id = :sr")
				.setParameter("sr", id)
				.getSingleResult();
	}
	
public boolean isBidPresent(int id) {
		
		return(Long)
				entityManager
				.createQuery("select count(b.bidAmount) from BidInfo b where b.sellRequest.id = :sr")
				.setParameter("sr", id)
				.getSingleResult() >= 1? true:false;
	}

public List<BidInfo> getFetchAllBid(int id) {
		
		return(List<BidInfo>)
				entityManager
				.createQuery("select b from BidInfo b where b.bidder.id = :br")
				.setParameter("br", id)
				.getResultList();
	}

public List<BidInfo> getFetchAllSellBid(int id) {
	
	return(List<BidInfo>)
			entityManager
			.createQuery("select b from BidInfo b where b.sellRequest.id = :sr order by b.bidAmount desc")
			.setParameter("sr", id)
			.getResultList();
}
}