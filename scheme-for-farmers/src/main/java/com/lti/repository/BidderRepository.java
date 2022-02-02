package com.lti.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lti.entity.BidInfo;
import com.lti.entity.Bidder;

@Repository
public class BidderRepository extends GenericRepository {

	public List<BidInfo> fetchBidInfoBasedOnBidderId(int id){
		
		return entityManager
				.createQuery("select a from BidInfo a where a.bidder.id =:id")
				.setParameter("id", id)
				.getResultList();
		
	}
}
