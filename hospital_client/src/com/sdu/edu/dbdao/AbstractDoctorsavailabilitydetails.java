package com.sdu.edu.dbdao;

import java.util.Date;

/**
 * AbstractDoctorsavailabilitydetails entity provides the base persistence
 * definition of the Doctorsavailabilitydetails entity. @author MyEclipse
 * Persistence Tools
 */

public abstract class AbstractDoctorsavailabilitydetails implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer doctorId;
	private Date dateofavailability;

	// Constructors

	/** default constructor */
	public AbstractDoctorsavailabilitydetails() {
	}

	/** full constructor */
	public AbstractDoctorsavailabilitydetails(Integer doctorId,
			Date dateofavailability) {
		this.doctorId = doctorId;
		this.dateofavailability = dateofavailability;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDoctorId() {
		return this.doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Date getDateofavailability() {
		return this.dateofavailability;
	}

	public void setDateofavailability(Date dateofavailability) {
		this.dateofavailability = dateofavailability;
	}

}