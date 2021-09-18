package com.flightapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.flightapp.model.AirLine;

@Repository
public interface AirLineRepository extends JpaRepository<AirLine, Integer> {

	@Query("SELECT airLine FROM AirLine airLine WHERE airLine.name = :name")
	List<AirLine> findByName(@Param("name") String name);

}
