package com.flightapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ticket")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ticketId;

	private String ticketNumber;

	private boolean isBooked;

	@Column(nullable = true)
	private String pnr;

	@OneToOne
	@JoinColumn(name = "tripId", nullable = false)
	private Trip trip;

	@OneToOne
	Passenger passenger;

	/**
	 * @return the ticketId
	 */
	public Long getTicketId() {
		return ticketId;
	}

	/**
	 * @param ticketId the ticketId to set
	 */
	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	/**
	 * @return the ticketNumber
	 */
	public String getTicketNumber() {
		return ticketNumber;
	}

	/**
	 * @param ticketNumber the ticketNumber to set
	 */
	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	/**
	 * @return the isBooked
	 */
	public boolean isBooked() {
		return isBooked;
	}

	/**
	 * @param isBooked the isBooked to set
	 */
	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}

	/**
	 * @return the pnr
	 */
	public String getPnr() {
		return pnr;
	}

	/**
	 * @param pnr the pnr to set
	 */
	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

	/**
	 * @return the trip
	 */
	public Trip getTrip() {
		return trip;
	}

	/**
	 * @param trip the trip to set
	 */
	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	/**
	 * @return the passenger
	 */
	public Passenger getPassenger() {
		return passenger;
	}

	/**
	 * @param passenger the passenger to set
	 */
	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

}
