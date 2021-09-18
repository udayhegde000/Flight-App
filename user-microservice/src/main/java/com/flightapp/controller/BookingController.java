package com.flightapp.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.flightapp.model.BookingHistory;
import com.flightapp.model.Passenger;
import com.flightapp.model.Ticket;
import com.flightapp.service.BookingHistoryService;

@RestController
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	BookingHistoryService bookingHistoryService;

	@PostMapping("/history/{emailId}")
	public List<BookingHistory> bookTicktets(@PathVariable String emailId) {
		return bookingHistoryService.findByEmail(emailId);
	}

	@PostMapping("/book/{tripId}")
	public BookingHistory bookTicktets(@PathVariable Long tripId,
			@RequestBody Map<Ticket, Passenger> ticketPassengerDetails, @RequestParam String name,
			@RequestParam String email, @RequestParam String meal, @RequestParam int noOfSeats) {

		BookingHistory bookingHistory = new BookingHistory();
		try {
			bookingHistory.setNoOfSeats(noOfSeats);
			bookingHistory.setEmail(email);
			bookingHistory.setMeal(meal);
			bookingHistory.setName(name);
			for (Entry<Ticket, Passenger> entry : ticketPassengerDetails.entrySet()) {
				Ticket ticket = entry.getKey();
				Passenger passenger = entry.getValue();
				HttpHeaders headers = new HttpHeaders();
				headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
				HttpEntity<Passenger> entity = new HttpEntity<Passenger>(passenger, headers);
				restTemplate.exchange("http:localhost:8091:/user/api/tickets/book/" + ticket.getTicketId(),
						HttpMethod.POST, entity, Ticket.class);
			}
			bookingHistory = bookingHistoryService.save(bookingHistory);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookingHistory;
	}

	@PostMapping("/book/{pnr}")
	public boolean cancelTicktet(@PathVariable String pnr) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Ticket> entity = new HttpEntity<Ticket>(headers);
		ResponseEntity<Object> responseEntity = restTemplate
				.exchange("http:localhost:8091:/user/api/tickets/cancel/" + pnr, HttpMethod.POST, entity, Object.class);
		if (responseEntity.getStatusCodeValue() == 200) {
			return true;
		} else {
			return false;
		}
	}
}
