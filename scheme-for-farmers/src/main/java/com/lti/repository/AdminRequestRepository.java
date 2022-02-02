package com.lti.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lti.entity.AdminRequest;
import com.lti.entity.SellRequest;

@Repository
public class AdminRequestRepository extends GenericRepository {

	public List<AdminRequest> fetchAllPendingRequest() {
		return entityManager.createQuery("select a from AdminRequest as a where a.status = 0").getResultList();
	}

	public List<AdminRequest> getExpiredSellRequests(LocalDate yest) {
		return entityManager.createQuery("select a.id from SellRequest a where a.endDate >= :date")
				.setParameter("date", yest)
				.getResultList();
	}
	
	public String getE(int sellRequestId ) {//get email id of max bidder from sell req id
		return (String) entityManager.createQuery("select a.bidder.email from BidInfo a where a.sellRequest.id =:id and a.bidAmount in (select max(c.bidAmount) from BidInfo c where c.sellRequest.id=:ids) ")
				.setParameter("id", sellRequestId)
				.setParameter("ids", sellRequestId)
				.getSingleResult();
	}

//	a.sellRequest.id =:id and a.bidAmount in (select max(a.bidAmount) from BidInfo a where id=:id)

}
