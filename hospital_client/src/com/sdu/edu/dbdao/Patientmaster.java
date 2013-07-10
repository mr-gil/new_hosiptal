package com.sdu.edu.dbdao;

import java.util.Date;

/**
 * Patientmaster entity. @author MyEclipse Persistence Tools
 */
public class Patientmaster extends AbstractPatientmaster implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Patientmaster() {
	}

	/** full constructor */
	public Patientmaster(String patientName, String patientGender,
			String patientEmail, Date patientDateofreg, Long patientPhonenumber) {
		super(patientName, patientGender, patientEmail, patientDateofreg,
				patientPhonenumber);
	}

}
