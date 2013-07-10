package com.sdu.edu.dbdao;

/**
 * Doctormaster entity. @author MyEclipse Persistence Tools
 */
public class Doctormaster extends AbstractDoctormaster implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Doctormaster() {
	}

	/** full constructor */
	public Doctormaster(String doctorName, String doctorGender,
			Double doctorFees, String doctorSpeciality, Long doctorPhonenumber,
			String doctorEmail) {
		super(doctorName, doctorGender, doctorFees, doctorSpeciality,
				doctorPhonenumber, doctorEmail);
	}

}
