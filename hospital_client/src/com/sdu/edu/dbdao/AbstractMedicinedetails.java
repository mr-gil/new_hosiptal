package com.sdu.edu.dbdao;

/**
 * AbstractMedicinedetails entity provides the base persistence definition of
 * the Medicinedetails entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractMedicinedetails implements java.io.Serializable {

	// Fields

	private Integer medicineId;
	private String medicineName;
	private String medicineDisease;
	private Double medicinePrice;

	// Constructors

	/** default constructor */
	public AbstractMedicinedetails() {
	}

	/** full constructor */
	public AbstractMedicinedetails(String medicineName, String medicineDisease,
			Double medicinePrice) {
		this.medicineName = medicineName;
		this.medicineDisease = medicineDisease;
		this.medicinePrice = medicinePrice;
	}

	// Property accessors

	public Integer getMedicineId() {
		return this.medicineId;
	}

	public void setMedicineId(Integer medicineId) {
		this.medicineId = medicineId;
	}

	public String getMedicineName() {
		return this.medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public String getMedicineDisease() {
		return this.medicineDisease;
	}

	public void setMedicineDisease(String medicineDisease) {
		this.medicineDisease = medicineDisease;
	}

	public Double getMedicinePrice() {
		return this.medicinePrice;
	}

	public void setMedicinePrice(Double medicinePrice) {
		this.medicinePrice = medicinePrice;
	}

}