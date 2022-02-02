package com.lti.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.dto.HistoryDto;
import com.lti.entity.AdminRequest;
import com.lti.entity.Farmer;
import com.lti.entity.LoginCredentials;
import com.lti.entity.SellRequest;
import com.lti.entity.UserType;
import com.lti.exception.FarmerException;
import com.lti.repository.AdminRequestRepository;
import com.lti.repository.BidInfoRepository;
import com.lti.repository.FarmerRepository;
import com.lti.repository.LoginRepository;
import com.lti.repository.SellRequestRepository;

/*public Farmer registerFarmer(Farmer fm);*/
@Service
@Transactional
public class FarmerServiceImpl implements FarmerService {
	@Autowired
	private FarmerRepository farmerRepository;

	@Autowired
	private BidInfoRepository bidInfoRepository;

	@Autowired
	private AdminRequestRepository adminRepository;

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private SellRequestImpl i;

	public int registerFarmer(Farmer fm) {
		// TODO Auto-generated method stub

		if (loginRepository.isUserPresent(fm.getEmail(), "FARMER"))
			// if(farmerRepository.isFarmerPresent(fm.getId()))
			throw new FarmerException("Farmer already registered!");
		else {
//			LoginCredentials c =new LoginCredentials();
//			c.setId("email@gmail");
//			c.setPassword("aaa");
//			c.setType("FARMER");

			// LoginCredentials c1 = (LoginCredentials) loginRepository.save(c);

			AdminRequest a = new AdminRequest();
			a.setStatus(false);
			a.setType(UserType.FARMER);
			AdminRequest updatedAdminReq = (AdminRequest) adminRepository.save(a);

			fm.setId(updatedAdminReq.getId());

//			fm.setEmail(c1.getId());
//			fm.setLogin(c1);

			// fm.setStatus(Status.PENDING);
			Farmer updatedFarmer = (Farmer) farmerRepository.save(fm);

			LoginCredentials login = new LoginCredentials();
			login.setType("FARMER");
			login.setId(fm.getEmail());
			loginRepository.save(login);

			// code to send emial to customer after successfull registration will be here
			return updatedFarmer.getId();
		}

	}

	public Farmer getFarmerDetails(int farmerId) {
		// TODO Auto-generated method stub
		return farmerRepository.fetch(Farmer.class, farmerId);
	}

//	public Farmer login(LoginCredentials c) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	public SellRequest placeSellRequest(SellRequest s) {
//		// TODO Auto-generated method stub
////		return null;
//		return i.placeSellRequest(s);
//	}

	public List<SellRequest> getMarketPlaceDetails(int farmerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Farmer editFarmerDetails(Farmer f) {
		// TODO Auto-generated method stub
		Farmer edit = (Farmer) farmerRepository.fetch(Farmer.class, f.getId());

		if (f.getAccountNo() != null)
			edit.setAccountNo(f.getAccountNo());
		if (f.getAddress() != null)
			edit.setAddress(f.getAddress());
		if (f.getArea() != null)
			edit.setArea(f.getArea());
		if (f.getCertificate() != null)
			edit.setCertificate(f.getCertificate());
		if (f.getEmail() != null)
			edit.setEmail(f.getEmail());
		if (f.getCity() != null)
			edit.setCity(f.getCity());
		if (f.getIfscCode() != null)
			edit.setIfscCode(f.getIfscCode());
		if (f.getMobileNo() != null)
			edit.setMobileNo(f.getMobileNo());
		if (f.getName() != null)
			edit.setName(f.getName());
		if (f.getPincode() != null)
			edit.setPincode(f.getPincode());
		if (f.getAadharNo() != null)
			edit.setAadharNo(f.getAadharNo());
		if (f.getState() != null)
			edit.setState(f.getState());

		Farmer edited = (Farmer) farmerRepository.save(edit);
		return edited;
	}

//	@Override
//	public List<SellRequest> getSellHistory(int id) {
//		Farmer fm = (Farmer) farmerRepository.fetch(Farmer.class, id);
//
//		LocalDate d = LocalDate.now();
//		List<SellRequest> list = fm.getSellRequests();
//		
//		List<SellRequest> updatedList = new ArrayList<SellRequest>();
//
//		for (SellRequest l : list) {
//			if (d.isAfter(l.getEndDate()))
//				updatedList.add(l);
//
//		}
//		return updatedList;
//	}

	@Override
	public List<HistoryDto> getSellHistory(int id) {
		Farmer fm = (Farmer) farmerRepository.fetch(Farmer.class, id);

		LocalDate d = LocalDate.now();
		List<SellRequest> list = fm.getSellRequests();

		List<HistoryDto> updatedList = new ArrayList<HistoryDto>();

		for (SellRequest l : list) {
			float maxBid = 0f;
			HistoryDto h = new HistoryDto();
			if (d.isAfter(l.getEndDate()))
				h.setStatus(false); // closed
			else
				h.setStatus(true);

			h.setCropName(l.getCropName());
			h.setBasePrice(l.getBasePrice());

			if (bidInfoRepository.isBidPresent(l.getId()))
				maxBid = bidInfoRepository.getMaximumBid(l.getId());
			h.setMaxBidAmount(maxBid);

			updatedList.add(h);

		}
		return updatedList;
	}
	
	
//update certificate
	public void updateCertificate(int id, String newFileName) {
		Farmer fm = (Farmer) farmerRepository.fetch(Farmer.class, id);
		fm.setCertificate(newFileName);
		farmerRepository.save(fm);
	}

	public Farmer get(int id) {
		return farmerRepository.fetch(Farmer.class, id);
	}

}
