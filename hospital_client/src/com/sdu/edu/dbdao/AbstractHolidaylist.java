package com.sdu.edu.dbdao;

import java.util.Date;

/**
 * AbstractHolidaylist entity provides the base persistence definition of the
 * Holidaylist entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractHolidaylist implements java.io.Serializable {

	// Fields

	private Date holidayDate;
	private String reason;

	// Constructors

	/** default constructor */
	public AbstractHolidaylist() {
	}

	/** minimal constructor */
	public AbstractHolidaylist(Date holidayDate) {
		this.holidayDate = holidayDate;
	}

	/** full constructor */
	public AbstractHolidaylist(Date holidayDate, String reason) {
		this.holidayDate = holidayDate;
		this.reason = reason;
	}

	// Property accessors

	public Date getHolidayDate() {
		return this.holidayDate;
	}

	public void setHolidayDate(Date holidayDate) {
		this.holidayDate = holidayDate;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}