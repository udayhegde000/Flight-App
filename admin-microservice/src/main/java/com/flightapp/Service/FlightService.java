package com.flightapp.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightapp.model.Flight;
import com.flightapp.repository.FlightRepository;

@Service
public class FlightService {

	@Autowired
	FlightRepository flightRepository;

	public List<Flight> findAll() {
		return flightRepository.findAll();
	}

	public List<Flight> findScheduledFlights() {
		return flightRepository.findScheduledFlights();
	}

	public Flight save(Flight flight) {
		return flightRepository.save(flight);
	}

	public Flight put(Long flightId, Flight flight) {
		Optional<Flight> flightOptional = flightRepository.findById(flightId);
		if (flightOptional.isPresent()) {
			flight.setFlightId(flightId);
			flight = save(flight);
			return flight;
		} else {
			return null;
		}
	}

	public void delete(Flight flight) {
		flightRepository.delete(flight);
	}

	public List<Flight> search(String airLine, String from, String to) {
		return flightRepository.search(airLine, from, to);
	}

	public Flight getById(Long flightId) {
		Optional<Flight> flight = flightRepository.findById(flightId);
		if (flight.isPresent()) {
			return flight.get();
		} else {
			return null;
		}
	}
}
