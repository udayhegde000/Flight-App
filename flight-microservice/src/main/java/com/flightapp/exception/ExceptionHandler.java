package com.flightapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(value = TripNotFoundException.class)
	public ResponseEntity<Object> exception(TripNotFoundException exception) {
		return new ResponseEntity<>("Trip not found", HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value = TicketNotFoundException.class)
	public ResponseEntity<Object> exception2(TicketNotFoundException exception) {
		return new ResponseEntity<>("Ticket not found", HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(value = InvalidPassengerException.class)
	public ResponseEntity<Object> exception3(InvalidPassengerException exception) {
		return new ResponseEntity<>("Invalid Passenger details", HttpStatus.NOT_FOUND);
	}
}
