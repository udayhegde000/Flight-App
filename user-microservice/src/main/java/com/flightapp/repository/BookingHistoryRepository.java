package com.flightapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.flightapp.model.BookingHistory;

@Repository
public interface BookingHistoryRepository extends JpaRepository<BookingHistory, Long> {

	@Query("SELECT bookingHistory FROM BookingHistory bookingHistory WHERE bookingHistory.email = :email")
	List<BookingHistory> findByEmail(@Param("email") String email);

}
