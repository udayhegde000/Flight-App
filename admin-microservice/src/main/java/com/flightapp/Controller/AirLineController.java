package com.flightapp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.Service.AirLineService;
import com.flightapp.model.AirLine;

@RestController
@RequestMapping("/airlines")
public class AirLineController {

	@Autowired
	AirLineService airLineService;

	@GetMapping("/getAll")
	public List<AirLine> getAll() {
		return airLineService.findAll();
	}

	@PostMapping("/save")
	public AirLine saveAirLine(@RequestBody AirLine airLine) {
		return airLineService.save(airLine);
	}

	@DeleteMapping("/delete")
	public void deleteAirLine(@RequestBody AirLine airLine) {
		airLineService.delete(airLine);
	}

	@PutMapping("/save")
	public AirLine saveAirLine(Long airLineId, @RequestBody AirLine airLine) {
		return airLineService.put(airLineId, airLine);
	}

	@GetMapping("/search/airLineId/{airLineId}")
	public AirLine searchAirLine(@PathVariable int airLineId) {
		return airLineService.findById(airLineId);
	}

	@GetMapping("/search/name/{name}")
	public List<AirLine> searchAirLine(@PathVariable String name) {
		return airLineService.findName(name);
	}
}
