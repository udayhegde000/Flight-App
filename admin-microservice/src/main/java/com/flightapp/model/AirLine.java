package com.flightapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "airline")
public class AirLine {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int airLineId;

	@Column(name = "name", nullable = true)
	private String name;

	private String uploadLogo;

	private String contactNumber;

	private String contactAddress;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the airLineId
	 */
	public int getAirLineId() {
		return airLineId;
	}

	/**
	 * @param airLineId the airLineId to set
	 */
	public void setAirLineId(int airLineId) {
		this.airLineId = airLineId;
	}

	/**
	 * @return the uploadLogo
	 */
	public String getUploadLogo() {
		return uploadLogo;
	}

	/**
	 * @param uploadLogo the uploadLogo to set
	 */
	public void setUploadLogo(String uploadLogo) {
		this.uploadLogo = uploadLogo;
	}

	/**
	 * @return the contactNumber
	 */
	public String getContactNumber() {
		return contactNumber;
	}

	/**
	 * @param contactNumber the contactNumber to set
	 */
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	/**
	 * @return the contactAddress
	 */
	public String getContactAddress() {
		return contactAddress;
	}

	/**
	 * @param contactAddress the contactAddress to set
	 */
	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

}
