/**
 * 
 */
package com.flightapp.Service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightapp.model.Passenger;
import com.flightapp.model.Ticket;
import com.flightapp.repository.TicketRepository;

/**
 * @author udayh
 *
 */
@Service
public class TicketService {

	@Autowired
	TicketRepository ticketRepository;

	public Ticket getById(Long ticketId) {
		Optional<Ticket> ticket = ticketRepository.findById(ticketId);
		if (ticket.isPresent()) {
			return ticket.get();
		} else {
			return null;
		}
	}

	public Ticket save(Ticket ticket) {
		return ticketRepository.save(ticket);
	}

	public Ticket put(Long ticketId, Ticket ticket) {
		Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
		if (optionalTicket.isPresent()) {
			ticket.setTicketId(ticketId);
			ticket = save(ticket);
			return ticket;
		} else {
			return null;
		}
	}

	public void delete(Ticket ticket) {
		ticketRepository.delete(ticket);
	}

	public Ticket book(Ticket ticket, Passenger passenger) {
		ticket.setBooked(true);
		UUID pnrUUID = UUID.randomUUID();
		ticket.setPassenger(passenger);
		ticket.setPnr(pnrUUID.toString());
		ticket = ticketRepository.save(ticket);
		return ticket;
	}

	public Ticket findByPnr(String pnr) {
		return ticketRepository.findByPnr(pnr);
	}
}
