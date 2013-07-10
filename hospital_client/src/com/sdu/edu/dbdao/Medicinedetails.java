package com.sdu.edu.dbdao;

/**
 * Medicinedetails entity. @author MyEclipse Persistence Tools
 */
public class Medicinedetails extends AbstractMedicinedetails implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Medicinedetails() {
	}

	/** full constructor */
	public Medicinedetails(String medicineName, String medicineDisease,
			Double medicinePrice) {
		super(medicineName, medicineDisease, medicinePrice);
	}

}
