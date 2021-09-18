package com.flightapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.flightapp.model.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

	@Query("SELECT flight from Flight flight where flight.isScheduled = :true")
	List<Flight> findScheduledFlights();

	@Query("SELECT flight from Flight flight where flight.fromPlace =:from AND flight.toPlace =:to AND flight.airLine.name =:airLine")
	List<Flight> search(@Param("airLine") String airLine, @Param("from") String from, @Param("to") String to);

}
