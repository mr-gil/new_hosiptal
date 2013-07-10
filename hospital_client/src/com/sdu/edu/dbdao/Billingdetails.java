package com.sdu.edu.dbdao;

import java.util.Date;

/**
 * Billingdetails entity. @author MyEclipse Persistence Tools
 */
public class Billingdetails extends AbstractBillingdetails implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Billingdetails() {
	}

	/** full constructor */
	public Billingdetails(Date billDate, Integer patientId, Double doctorfees,
			Double medicinesamount, Integer bedId, Long numberOfDaysAdmitted,
			Double bedfeesFees, Double totalamount) {
		super(billDate, patientId, doctorfees, medicinesamount, bedId,
				numberOfDaysAdmitted, bedfeesFees, totalamount);
	}

}
