package com.flightapp.model;

import java.util.Date;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;

@Component
public class Flight {

	private Long flightId;

	private String flightNumber;

	private AirLine airLine;

	private Date startDate;

	private Date endDate;

	private String fromPlace;

	private String toPlace;

	private String instrument;

	private int noOfSeats;

	private Double cost;

	private int numberOfRows;

	private String meal;

	private String flightFrequency;

	private boolean isScheduled;

	/**
	 * @return the flightId
	 */
	public Long getFlightId() {
		return flightId;
	}

	/**
	 * @param flightId the flightId to set
	 */
	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}

	/**
	 * @return the flightNumber
	 */
	public String getFlightNumber() {
		return flightNumber;
	}

	/**
	 * @param flightNumber the flightNumber to set
	 */
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	/**
	 * @return the airLine
	 */
	public AirLine getAirLine() {
		return airLine;
	}

	/**
	 * @param airLine the airLine to set
	 */
	public void setAirLine(AirLine airLine) {
		this.airLine = airLine;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the fromPlace
	 */
	public String getFromPlace() {
		return fromPlace;
	}

	/**
	 * @param fromPlace the fromPlace to set
	 */
	public void setFromPlace(String fromPlace) {
		this.fromPlace = fromPlace;
	}

	/**
	 * @return the toPlace
	 */
	public String getToPlace() {
		return toPlace;
	}

	/**
	 * @param toPlace the toPlace to set
	 */
	public void setToPlace(String toPlace) {
		this.toPlace = toPlace;
	}

	/**
	 * @return the instrument
	 */
	public String getInstrument() {
		return instrument;
	}

	/**
	 * @param instrument the instrument to set
	 */
	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}

	/**
	 * @return the noOfSeats
	 */
	public int getNoOfSeats() {
		return noOfSeats;
	}

	/**
	 * @param noOfSeats the noOfSeats to set
	 */
	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	/**
	 * @return the cost
	 */
	public Double getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(Double cost) {
		this.cost = cost;
	}

	/**
	 * @return the numberOfRows
	 */
	public int getNumberOfRows() {
		return numberOfRows;
	}

	/**
	 * @param numberOfRows the numberOfRows to set
	 */
	public void setNumberOfRows(int numberOfRows) {
		this.numberOfRows = numberOfRows;
	}

	/**
	 * @return the meal
	 */
	public String getMeal() {
		return meal;
	}

	/**
	 * @param meal the meal to set
	 */
	public void setMeal(String meal) {
		this.meal = meal;
	}

	/**
	 * @return the isScheduled
	 */
	public boolean isScheduled() {
		return isScheduled;
	}

	/**
	 * @param isScheduled the isScheduled to set
	 */
	public void setScheduled(boolean isScheduled) {
		this.isScheduled = isScheduled;
	}

	/**
	 * @return the flightFrequency
	 */
	public String getFlightFrequency() {
		return flightFrequency;
	}

	/**
	 * @param flightFrequency the flightFrequency to set
	 */
	public void setFlightFrequency(String flightFrequency) {
		this.flightFrequency = flightFrequency;
	}

	/**
	 * creates the trips and Starts the flight schedule
	 * 
	 * @TODO
	 */
	public void start() {

	}
}
