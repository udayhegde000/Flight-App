package com.flightapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightapp.model.BookingHistory;
import com.flightapp.repository.BookingHistoryRepository;

@Service
public class BookingHistoryService {

	@Autowired
	BookingHistoryRepository bookingHistoryRepository;

	public BookingHistory save(BookingHistory bookingHistory) {
		return bookingHistoryRepository.save(bookingHistory);
	}

	public List<BookingHistory> findByEmail(String emailId) {
		return bookingHistoryRepository.findByEmail(emailId);
	}
}
