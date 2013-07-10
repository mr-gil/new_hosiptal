package com.sdu.edu.dbdao;

import java.util.Date;

/**
 * Doctorsavailabilitydetails entity. @author MyEclipse Persistence Tools
 */
public class Doctorsavailabilitydetails extends
		AbstractDoctorsavailabilitydetails implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Doctorsavailabilitydetails() {
	}

	/** full constructor */
	public Doctorsavailabilitydetails(Integer doctorId, Date dateofavailability) {
		super(doctorId, dateofavailability);
	}

}
