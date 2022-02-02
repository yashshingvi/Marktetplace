package com.lti.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.dto.PlaceSellRequest;
import com.lti.dto.SearchDto;
import com.lti.entity.AdminRequest;
import com.lti.entity.Farmer;
import com.lti.entity.SellRequest;
import com.lti.entity.Status;
import com.lti.entity.UserType;
import com.lti.exception.SellRequestException;
import com.lti.repository.AdminRequestRepository;
import com.lti.repository.FarmerRepository;
import com.lti.repository.SellRequestRepository;

@Service
@Transactional
public class SellRequestImpl implements SellRequestService {

	@Autowired
	private SellRequestRepository sellRequestRepository;

	@Autowired
	private AdminRequestRepository adminRepository;

	@Autowired
	private FarmerRepository farmerRepository;

	public List<PlaceSellRequest> getActiveSellRequest() {

		List<PlaceSellRequest> list = new ArrayList<PlaceSellRequest>();
		
		
		List<Object[]> result = sellRequestRepository.fetchAllActiveBids();
		
		for (Object[] obj : result)
		{
			PlaceSellRequest s = new PlaceSellRequest();
			s.setId((int)obj[0]);
			s.setCropName((String)obj[1]);
			s.setCropType((String)obj[2]);
			s.setBasePrice((double)obj[3]);
			s.setQuantity((int)obj[4]);
			s.setPhValue((int)obj[5]);
			s.setFertlizerType((String)obj[6]);
			s.setStartDate((LocalDate)obj[7]);
			s.setEndDate((LocalDate)obj[8]);
			list.add(s);
			
		}
			return list;
	}

	public SellRequest placeSellRequestByValidFarmer(PlaceSellRequest s) {

		int id = s.getSellerId();

		// SellRequest sellreq = new SellRequest();

//		if (id == 0)
//			throw new SellRequestException("FarmerId cannot be null");

		Farmer fm = farmerRepository.fetch(Farmer.class, id);

		if (fm == null)
			throw new SellRequestException("FarmerId is not valid");

		if (fm.getStatus() == Status.PENDING || fm.getStatus() == Status.REJECTED)
			throw new SellRequestException("Your Id is Not Approved By Admin");

		if (s.getStartDate() == null || sellRequestRepository.CheckStartDate(s.getStartDate()))
			throw new SellRequestException("StartDate cannot be less than today's date");
		
		LocalDate d1 = LocalDate.now();
		long days = ChronoUnit.DAYS.between(d1,s.getStartDate());
		if(days > 30)
			throw new SellRequestException("StartDate must be within 30 days");

		AdminRequest a = new AdminRequest();
		a.setStatus(false);
		a.setType(UserType.SELLREQUEST);
		AdminRequest updatedAdminReq = (AdminRequest) adminRepository.save(a);

		SellRequest sellreq = new SellRequest();

		sellreq.setId(updatedAdminReq.getId());
		sellreq.setBasePrice(s.getBasePrice());
		sellreq.setCropName(s.getCropName());
		sellreq.setCropType(s.getCropType());
		sellreq.setFertlizerType(s.getFertlizerType());
		sellreq.setPhValue(s.getPhValue());
		sellreq.setQuantity(s.getQuantity());
		sellreq.setStartDate(s.getStartDate());
		sellreq.setEndDate(s.getEndDate());
		sellreq.setStatus(Status.PENDING);
		sellreq.setFarmer(fm);

		SellRequest updatedSellReq = (SellRequest) sellRequestRepository.save(sellreq);
		return updatedSellReq;
	}

	@Override
	public SellRequest getSellReqDetails(int id) {
		SellRequest sellreq = sellRequestRepository.fetch(SellRequest.class, id);
		return sellreq;
	}

	@Override
	public List<PlaceSellRequest> filterCrops(List<PlaceSellRequest> list, SearchDto search) {

		if(search.getCropName() == null && search.getFertlizerType() == null)
		  return list;
		else if(search.getCropName() == null)
	     	return sellRequestRepository.fetchCropByType(list,search.getFertlizerType());
		else if(search.getFertlizerType() == null)
			return sellRequestRepository.fetchCropByName(list,search.getCropName());
		else
		    return sellRequestRepository.fetchByBoth(list,search.getCropName(),search.getFertlizerType());
		
	}

}
