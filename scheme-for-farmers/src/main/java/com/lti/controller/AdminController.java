package com.lti.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.Status;
import com.lti.dto.Status.StatusType;
import com.lti.entity.AdminRequest;
import com.lti.exception.AdminRequestException;
import com.lti.service.AdminService;
import com.lti.service.BidService;

@RestController
@CrossOrigin
public class AdminController {

	@Autowired
	private AdminService adminRequest;
	
	@Autowired
	private BidService bidRequest;

	@Autowired
	private JavaMailSender mailSender;

//	scheduler.schedule(task, new CronTrigger("* * * * *"));
	@Scheduled(cron = "5 17 11 * * *", zone = "Asia/Calcutta")
	public void sendEmailToWinner() {
		LocalDate yest = LocalDate.now().minusDays(2);
		List<Integer> sellRequestIds =new ArrayList<>();
		sellRequestIds=adminRequest.getRecentExpiredSellRequests(yest);
//		int l=sellRequestIds.size();
		List<String> bidderIds=new ArrayList<>();
		for(int i:sellRequestIds) {
			System.out.println(i);
			bidderIds.add(adminRequest.getEmailId(i));
			System.out.println(adminRequest.getEmailId(i));
		}
		String [] emailRecepients = new String[bidderIds.size()];
		for(int i=0;i<bidderIds.size();i++) {
			emailRecepients[i]=bidderIds.get(i);
		}
		System.out.println("hello");
		Status s = new Status();
		String [] emailRecepientsCheck = new String[] {"yashshingvi@gmail.com","poornimasuba496@gmail.com","akhilsontakkeas@gmail.com","apoorva.bid12@gmail.com"};
		String from = "ltiproject59@gmail.com";
//		String to = "poornimasuba496@gmail.com";

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		try {
			helper.setSubject("Congratulations!!");
			helper.setFrom(from);
			helper.setTo(emailRecepientsCheck);
			boolean html = true;
			helper.setText("<b>Congratulations, you have won the bidding please Login</i>", html);
			s.setMessage("approved");
			s.setStatus(StatusType.SUCCESS);
		} catch (MessagingException e) {
			s.setMessage(e.getMessage());
			s.setStatus(StatusType.SUCCESS);
		}

		mailSender.send(message);
//		return s;
//		sendEmail(emailRecepients);
	}

	@RequestMapping("/send-email")
	public Status sendEmail() {
		Status s = new Status();

		String from = "poornimaofficial496@gmail.com";
		String to = "poornimasuba496@gmail.com";

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		try {
			helper.setSubject("This is an HTML email");
			helper.setFrom(from);
			helper.setTo(to);
			boolean html = true;
			helper.setText("<b>Hey guys</b>,<br><i>Welcome to my new home</i>", html);
			s.setMessage("approved");
			s.setStatus(StatusType.SUCCESS);
		} catch (MessagingException e) {
			s.setMessage(e.getMessage());
			s.setStatus(StatusType.SUCCESS);
		}

		mailSender.send(message);
		return s;
	}

	@GetMapping("/show-requests")
	public List<AdminRequest> getPendingRequest() {

		List<AdminRequest> list = adminRequest.getAllPendingRequests();
		return list;
	}

	@PostMapping("/approve-status")
	public Status ApproveStatus(@RequestBody AdminRequest admin) {
		Status s = new Status();
		try {
			AdminRequest a = adminRequest.approveStatus(admin);
			if (a.isStatus()) {
				s.setMessage("approved");
				s.setStatus(StatusType.SUCCESS);
				return s;
			} else {
				a.setStatus(true);
				adminRequest.UpdateAdminStatus(a);
				s.setMessage("rejected");
				s.setStatus(StatusType.SUCCESS);
				return s;
			}
		} catch (AdminRequestException e) {
			s.setMessage(e.getMessage());
			s.setStatus(StatusType.FAILED);
			return s;
		}
	}
}
