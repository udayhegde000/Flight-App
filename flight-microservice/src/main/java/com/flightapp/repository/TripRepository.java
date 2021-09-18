/**
 * 
 */
package com.flightapp.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.flightapp.model.Trip;

/**
 * @author udayh
 *
 */
@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

	@Transactional
	@Modifying
	@Query("DELETE FROM Trip trip WHERE trip.flightId = :flightId")
	void deleteAllByFlightId(@Param("flightId") Long flightId);

	@Query("SELECT trip FROM Trip trip WHERE trip.flightId = :flightId")
	List<Trip> getByFlightId(@Param("flightId") Long flightId);

	@Query("SELECT trip FROM Trip trip WHERE trip.departureTime >= :journeyDate")
	List<Trip> search(@Param("journeyDate")Date journeyDate);

}
