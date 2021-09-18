package com.flightapp.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightapp.model.AirLine;
import com.flightapp.repository.AirLineRepository;

@Service
public class AirLineService {

	@Autowired
	AirLineRepository airLineRepository;

	public AirLine save(AirLine airLine) {
		return airLineRepository.save(airLine);
	}

	public void delete(AirLine airLine) {
		airLineRepository.delete(airLine);
	}

	public AirLine put(Long airLineId, AirLine airLine) {
		Optional<AirLine> airLineOptional = airLineRepository.findById(airLineId.intValue());
		if (airLineOptional.isPresent()) {
			airLine.setAirLineId(airLineId.intValue());
			airLine = save(airLine);
			return airLine;
		} else {
			return null;
		}
	}

	public List<AirLine> findAll() {
		return airLineRepository.findAll();
	}

	public AirLine findById(int airLineId) {
		Optional<AirLine> optionalAirLine = airLineRepository.findById(airLineId);
		if (optionalAirLine.isPresent()) {
			return optionalAirLine.get();
		} else {
			return null;
		}
	}

	public List<AirLine> findName(String name) {
		return airLineRepository.findByName(name);
	}
}
