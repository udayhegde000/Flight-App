/**
 * 
 */
package com.flightapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.flightapp.model.Ticket;

/**
 * @author udayh
 *
 */
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

	@Query("SELECT ticket FROM Ticket ticket WHERE ticket.trip.flightId = :flightId")
	List<Ticket> findByFlightId(@Param("flightId") Long flightId);

	@Query("SELECT ticket FROM Ticket ticket WHERE ticket.trip.tripId = :tripId")
	List<Ticket> findByTripId(@Param("tripId") Long tripId);

	@Query("SELECT ticket FROM Ticket ticket WHERE ticket.pnr = :pnr")
	Ticket findByPnr(@Param("pnr") String pnr);

}
