package com.lti.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lti.entity.SellRequest;
import com.lti.entity.UserType;

@Repository
public class FarmerRepository extends GenericRepository{
	public  boolean isFarmerPresent(int id) {
		 return (Long) entityManager.createQuery("select count(f.id) from Farmer f where f.id = :id ")
					.setParameter("id", id)
					.getSingleResult() == 1 ?true:false;
	}
	
//	public List<SellRequest> fetchSellHistory(int id)
//	{
//		LocalDate d1 = LocalDate.now();
//		return 
//				entityManager
//				.createQuery("select s from SellRequest s join s.farmer f where f.id = :sid and to_char(s.endDate,'YYYY-MM-DD') <= to_char(:em,'YYYY-MM-DD') ")
//                .setParameter("sid", id)
//				.setParameter("em", d1)
//				.getResultList();
//	}
	
	public List<String> fetchSellHistory(int id)
	{
//		LocalDate d1 = LocalDate.now();
		return (List<String>)
				entityManager
				.createQuery("select s.cropName from SellRequest s where id=:sid")
                .setParameter("sid", id)
				.getResultList();
	}
}
