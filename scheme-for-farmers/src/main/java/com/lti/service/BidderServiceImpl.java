package com.lti.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.dto.BidderHistoryDto;
import com.lti.dto.HistoryDto;
import com.lti.dto.PlaceBidByBidder;
import com.lti.entity.AdminRequest;
import com.lti.entity.BidInfo;
import com.lti.entity.Bidder;
import com.lti.entity.Farmer;
import com.lti.entity.LoginCredentials;
import com.lti.entity.SellRequest;
import com.lti.entity.UserType;
import com.lti.exception.BidderRegisterException;
import com.lti.repository.AdminRequestRepository;
import com.lti.repository.BidInfoRepository;
import com.lti.repository.BidderRepository;
import com.lti.repository.LoginRepository;
import com.lti.repository.SellRequestRepository;

@Service
@Transactional
public class BidderServiceImpl implements BidderService {

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private BidderRepository bidderRepository;
	
	@Autowired
	private BidInfoRepository bidInfoRepository;

	@Autowired
	private SellRequestRepository sellRequestRepository;

	@Autowired
	private AdminRequestRepository adminRepository;

	@Override
	public Bidder registerBidder(Bidder b) {

		if (loginRepository.isUserPresent(b.getEmail(), "BIDDER"))
			throw new BidderRegisterException("Bidder already present");

		AdminRequest a = new AdminRequest();
		a.setStatus(false);
		a.setType(UserType.BIDDER);
		AdminRequest updatedAdminReq = (AdminRequest) adminRepository.save(a);

		b.setId(updatedAdminReq.getId());
		Bidder bidder = (Bidder) bidderRepository.save(b);

		LoginCredentials login = new LoginCredentials();
		login.setType("BIDDER");
		login.setId(b.getEmail());
		loginRepository.save(login);

		return bidder;

	}

	@Override
	public Bidder getBidderDetails(int bidderId) {
		Bidder bidder = (Bidder) bidderRepository.fetch(Bidder.class, bidderId);
		return bidder;
	}

	@Override
	public List<SellRequest> getAvailableBids() {

		return null;
	}

	@Override
	public Bidder editProfileDetails(Bidder b) {
		// cannot edit bidder Id
		Bidder old = bidderRepository.fetch(Bidder.class, b.getId());

		if (b.getAccountNo() != null)
			old.setAccountNo(b.getAccountNo());
		if (b.getAddress() != null)
			old.setAddress(b.getAddress());
		if (b.getAdhaarNo() != null)
			old.setAdhaarNo(b.getAdhaarNo());
		if (b.getCity() != null)
			old.setCity(b.getCity());
		if (b.getEmail() != null)
			old.setEmail(b.getEmail());
		if (b.getIfscCode() != null)
			old.setIfscCode(b.getIfscCode());
		if (b.getMobileNo() != null)
			old.setMobileNo(b.getMobileNo());
		if (b.getName() != null)
			old.setName(b.getName());
		if (b.getPincode() != null)
			old.setPincode(b.getPincode());
		if (b.getState() != null)
			old.setState(b.getState());

		Bidder updatedBidder = (Bidder) bidderRepository.save(old);
		return updatedBidder;
	}

	@Override
	public BidInfo placeBid(PlaceBidByBidder placeBid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BidderHistoryDto> getHistory(int id) {
		List<BidInfo> list = bidderRepository.fetchBidInfoBasedOnBidderId(id);

		List<BidderHistoryDto> updatedList = new ArrayList<BidderHistoryDto>();
		LocalDate d1 = LocalDate.now();

		for (BidInfo l : list) {
			
			float maxBid = 0f;
			BidderHistoryDto h = new BidderHistoryDto();
			
			BidInfo b = bidInfoRepository.fetch(BidInfo.class, l.getId());
			
			SellRequest s = b.getSellRequest();

			if (d1.isAfter(s.getEndDate()))
				h.setStatus(false); // closed
			else
				h.setStatus(true);  //live

			h.setCropName(s.getCropName());
			h.setBasePrice(s.getBasePrice());
			h.setHisBidAmount(l.getBidAmount());

			if (bidInfoRepository.isBidPresent(s.getId()))
				maxBid = bidInfoRepository.getMaximumBid(s.getId());
			h.setMaxBidAmount(maxBid);

			updatedList.add(h);

		}
		return updatedList;
	}

}
