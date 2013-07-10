package com.sdu.edu.dbdao;

import java.util.Date;

/**
 * Holidaylist entity. @author MyEclipse Persistence Tools
 */
public class Holidaylist extends AbstractHolidaylist implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Holidaylist() {
	}

	/** minimal constructor */
	public Holidaylist(Date holidayDate) {
		super(holidayDate);
	}

	/** full constructor */
	public Holidaylist(Date holidayDate, String reason) {
		super(holidayDate, reason);
	}

}
