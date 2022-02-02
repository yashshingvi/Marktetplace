package com.lti.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lti.dto.PlaceSellRequest;
import com.lti.entity.SellRequest;

@Repository
public class SellRequestRepository extends GenericRepository {

	public List<Object[]> fetchAllActiveBids() {
		LocalDate d1 = LocalDate.now();
		System.out.println(d1);
		return entityManager.createQuery(
				"select s.id,s.cropName,s.cropType,s.basePrice,s.quantity,s.phValue,s.fertlizerType,s.startDate,s.endDate"
						+ " from SellRequest s where s.status=0 and to_char(s.endDate,'YYYY-MM-DD') >= to_char(:em,'YYYY-MM-DD') ")
				.setParameter("em", d1).getResultList();
	}

	public boolean CheckStartDate(LocalDate date) {
		LocalDate d1 = LocalDate.now();
		if (date.isAfter(d1))
			return false;
		else
			return true;

	}

	public double getBasePrice(int id) {

		return (Double) entityManager.createQuery("select s.basePrice from SellRequest s where s.id = :sr")
				.setParameter("sr", id).getSingleResult();
	}

	public List<PlaceSellRequest> fetchCropByName(List<PlaceSellRequest> list, String cropName) {

		List<PlaceSellRequest> filtered = new ArrayList<PlaceSellRequest>();

		for (PlaceSellRequest p : list) {
			if (p.getCropName().equals(cropName))
				filtered.add(p);
		}
		return filtered;

	}

	public List<PlaceSellRequest> fetchCropByType(List<PlaceSellRequest> list, String fertType) {

		List<PlaceSellRequest> filtered = new ArrayList<PlaceSellRequest>();

		for (PlaceSellRequest p : list) {
			if (p.getFertlizerType().equals(fertType))
				filtered.add(p);
		}
		return filtered;

	}

	public List<PlaceSellRequest> fetchByBoth(List<PlaceSellRequest> list, String cropName , String fertType) {

		List<PlaceSellRequest> filtered = new ArrayList<PlaceSellRequest>();

		for (PlaceSellRequest p : list) {
			if (p.getCropName().equals(cropName) && p.getFertlizerType().equals(fertType))
				filtered.add(p);
		}
		return filtered;

	}
}
