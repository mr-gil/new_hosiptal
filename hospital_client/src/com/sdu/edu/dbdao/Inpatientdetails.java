package com.sdu.edu.dbdao;

import java.util.Date;

/**
 * Inpatientdetails entity. @author MyEclipse Persistence Tools
 */
public class Inpatientdetails extends AbstractInpatientdetails implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Inpatientdetails() {
	}

	/** minimal constructor */
	public Inpatientdetails(Integer patientId) {
		super(patientId);
	}

	/** full constructor */
	public Inpatientdetails(Integer patientId, Date dateofadmission,
			Integer bedId, Date dateOfDischarge, String comments,
			Integer doctorId) {
		super(patientId, dateofadmission, bedId, dateOfDischarge, comments,
				doctorId);
	}

}
