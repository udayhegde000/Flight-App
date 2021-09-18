package com.flightapp.Controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.Service.PassengerService;
import com.flightapp.Service.TicketService;
import com.flightapp.exception.TicketNotFoundException;
import com.flightapp.model.Passenger;
import com.flightapp.model.Ticket;

/**
 * 
 * @author udayh
 *
 */
@RestController
@RequestMapping("tickets")
public class TicketController {

	@Autowired
	TicketService ticketService;

	@Autowired
	PassengerService pasengerSerice;

	@GetMapping("/get/{ticketId}")
	public Ticket getTicket(@PathVariable Long ticketId) {
		return ticketService.getById(ticketId);
	}

	@PostMapping("/save")
	public Ticket save(@RequestBody Ticket ticket) {
		return ticketService.save(ticket);
	}

	@PutMapping("/save/{ticketId}")
	public Ticket save(@PathVariable Long ticketId, @RequestBody Ticket ticket) {
		return ticketService.put(ticketId, ticket);
	}

	@DeleteMapping("/delete")
	public void Delete(@RequestBody Ticket ticket) {
		ticketService.delete(ticket);
	}

	@PostMapping("/book/{ticketId}")
	public Ticket book(@PathVariable Long ticketId, @RequestBody Passenger pasenger) {
		Ticket ticket = ticketService.getById(ticketId);
		// pasenger = pasengerSerice.save(pasenger);
		if (ticket != null && !ticket.isBooked()) {
			return ticketService.book(ticket, pasenger);
		} else {
			// throw exception
			throw new TicketNotFoundException();
		}

	}

	@GetMapping("/download/{pnr}")
	public Ticket downloadTicket(@PathVariable String pnr) {
		Ticket ticket = ticketService.findByPnr(pnr);
		if (ticket.isBooked()) {
			return ticket;
		} else {
			throw new TicketNotFoundException();
		}

	}

	@PutMapping("/cancel/{pnr}")
	public Ticket cancelTicket(@PathVariable String pnr) {
		Ticket ticket = ticketService.findByPnr(pnr);
		if (ticket == null) {
			throw new TicketNotFoundException();
		}
		Calendar now = Calendar.getInstance();
		now.add(Calendar.HOUR, 24);
		if (now.after(ticket.getTrip().getDepartureTime())) {
			ticket.setBooked(false);
			ticket.setPnr(null);
			ticket.setPassenger(null);
			ticket = ticketService.save(ticket);
		}
		return ticket;
	}

}
