package com.sdu.edu.dbdao;

import java.util.Date;

/**
 * Appointmentsdetails entity. @author MyEclipse Persistence Tools
 */
public class Appointmentsdetails extends AbstractAppointmentsdetails implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Appointmentsdetails() {
	}

	/** full constructor */
	public Appointmentsdetails(Integer patientId, Integer doctorId, Date date,
			String time, String ava) {
		super(patientId, doctorId, date, time, ava);
	}

}
