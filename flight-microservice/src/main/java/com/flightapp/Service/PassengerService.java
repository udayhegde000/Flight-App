/**
 * 
 */
package com.flightapp.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightapp.model.Passenger;
import com.flightapp.repository.PassengerRepository;

/**
 * @author udayh
 *
 */

@Service
public class PassengerService {

	@Autowired
	PassengerRepository passengerRepository;

	public Passenger save(Passenger passenger) {
		return passengerRepository.save(passenger);
	}

	public List<Passenger> getAll() {
		return passengerRepository.findAll();
	}

}
