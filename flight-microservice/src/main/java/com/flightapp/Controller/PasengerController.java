package com.flightapp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.Service.PassengerService;
import com.flightapp.exception.InvalidPassengerException;
import com.flightapp.model.Passenger;

@RestController
@RequestMapping("/passengers")
public class PasengerController {

	@Autowired
	PassengerService passengerService;

	@GetMapping("/getAll")
	public List<Passenger> getAll() {
		return passengerService.getAll();
	}

	@PostMapping("/save")
	public Passenger save(@RequestBody Passenger passenger) {
		if(passenger==null) {
			throw new InvalidPassengerException();
		}
		return passengerService.save(passenger);
	}
}
