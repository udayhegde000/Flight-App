/**
 * 
 */
package com.flightapp.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightapp.model.Flight;
import com.flightapp.model.Ticket;
import com.flightapp.model.Trip;
import com.flightapp.repository.PassengerRepository;
import com.flightapp.repository.TicketRepository;
import com.flightapp.repository.TripRepository;

/**
 * @author udayh
 *
 */
@Service
public class TripService {

	@Autowired
	TripRepository tripRepository;

	@Autowired
	TicketRepository ticketRepository;

	@Autowired
	PassengerRepository passengerRepository;

	public List<Trip> findAll() {
		return tripRepository.findAll();

	}

	public Trip save(Trip trip) {
		return tripRepository.save(trip);
	}

	public Trip put(Long tripId, Trip trip) {
		Optional<Trip> optionalTrip = tripRepository.findById(tripId);
		if (optionalTrip.isPresent()) {
			trip.setTripId(tripId);
			trip = save(trip);
			return trip;
		} else {
			return null;
		}
	}

	public void delete(Trip trip) {
		tripRepository.delete(trip);
	}

	public List<Trip> startTrips(Flight flight) {
		List<Trip> tripList = new ArrayList<>();
		Date startDate = flight.getStartDate();
		Date endDate = flight.getEndDate();
		Date now = new Date();
		if (now.before(startDate) && startDate.before(endDate)) {
			Calendar start = Calendar.getInstance();
			start.setTime(startDate);
			Calendar end = Calendar.getInstance();
			end.setTime(endDate);

			Calendar tempEnd = Calendar.getInstance();
			tempEnd.setTimeInMillis(tempEnd.getTime().getTime() + end.getTime().getTime() - start.getTime().getTime());

			String flightFrequency = flight.getFlightFrequency();
			if (flightFrequency != null && !flightFrequency.isEmpty()) {
				switch (flightFrequency) {
				case "Daily":
					while (start.before(end)) {
						Trip trip = new Trip();
						trip.setDepartureTime(start.getTime());
						trip.setArrivalTime(tempEnd.getTime());
						trip.setFlightId(flight.getFlightId());
						trip = tripRepository.save(trip);
						tripList.add(startTrip(trip, flight.getNoOfSeats()));
						start.add(Calendar.DATE, 1);
						tempEnd.add(Calendar.DATE, 1);
					}
					break;
				case "Week Days Only":
					while (start.before(end)) {
						boolean isWeekday = ((start.get(Calendar.DAY_OF_WEEK) >= Calendar.MONDAY)
								&& (start.get(Calendar.DAY_OF_WEEK) <= Calendar.FRIDAY));
						if (isWeekday) {
							Trip trip = new Trip();
							trip.setDepartureTime(start.getTime());
							trip.setArrivalTime(tempEnd.getTime());
							trip.setFlightId(flight.getFlightId());
							trip = tripRepository.save(trip);
							tripList.add(startTrip(trip, flight.getNoOfSeats()));
						}
						start.add(Calendar.DATE, 1);
						tempEnd.add(Calendar.DATE, 1);
					}
					break;
				case "Week End Only":
					while (start.before(end)) {
						boolean isWeekEnd = ((start.get(Calendar.DAY_OF_WEEK) < Calendar.MONDAY)
								&& (start.get(Calendar.DAY_OF_WEEK) > Calendar.FRIDAY));
						if (isWeekEnd) {
							Trip trip = new Trip();
							trip.setDepartureTime(start.getTime());
							trip.setArrivalTime(tempEnd.getTime());
							trip.setFlightId(flight.getFlightId());
							trip = tripRepository.save(trip);
							tripList.add(startTrip(trip, flight.getNoOfSeats()));
						}
						start.add(Calendar.DATE, 1);
						tempEnd.add(Calendar.DATE, 1);
					}
					break;
				}
			}

		}
		return tripList;
	}

	public void stopFlightSchedule(Flight flight) {
		List<Trip> trips = tripRepository.getByFlightId(flight.getFlightId());
		List<Ticket> ticketList = ticketRepository.findByFlightId(flight.getFlightId());

		if (!ticketList.isEmpty()) {
			ticketRepository.deleteAll(ticketList);
		}
		if (!trips.isEmpty()) {
			tripRepository.deleteAll(trips);
		}
	}

	public Trip startTrip(Trip trip, int totalTickets) {
		List<Ticket> ticketList = new ArrayList<>();
		for (int i = 1; i <= totalTickets; i++) {
			Ticket ticket = new Ticket();
			ticket.setTrip(trip);
			ticket.setTicketNumber(Character.toString(i % 26) + i);
			ticketList.add(ticket);
		}
		ticketRepository.saveAll(ticketList);

		trip.setScheduled(true);
		trip = save(trip);
		return trip;
	}

	public void endTrip(Trip trip) {
		List<Ticket> ticketList = ticketRepository.findByTripId(trip.getTripId());
		ticketRepository.deleteAll(ticketList);
		tripRepository.delete(trip);
	}

	public void endTrips(Flight flight) {
		List<Trip> tripList = tripRepository.getByFlightId(flight.getFlightId());
		for (Trip trip : tripList) {
			endTrip(trip);
		}
	}

	public List<Trip> search(Date journeyDate, String fromPlace, String toPlace, Flight[] flights) {
		// get the flights scheduled from rest template
		List<Trip> tripList = tripRepository.search(journeyDate);
		if (flights == null || flights.length < 1) {
			return tripList;
		}
		Map<Long, Flight> flightMap = new HashMap<>();
		for (int i = 0; i < flights.length; i++) {
			flightMap.put(flights[i].getFlightId(), flights[i]);
		}
		Iterator<Trip> tripIterator = tripList.iterator();
		while (tripIterator.hasNext()) {
			Trip trip = tripIterator.next();
			Flight flight = flightMap.get(trip.getFlightId());
			if (flight == null || !flight.getFromPlace().contains(fromPlace)
					|| !flight.getToPlace().contains(toPlace)) {
				tripIterator.remove();
			}
		}
		return tripList;
	}
}
