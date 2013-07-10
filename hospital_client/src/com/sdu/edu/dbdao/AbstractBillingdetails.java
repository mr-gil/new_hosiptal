package com.sdu.edu.dbdao;

import java.util.Date;

/**
 * AbstractBillingdetails entity provides the base persistence definition of the
 * Billingdetails entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractBillingdetails implements java.io.Serializable {

	// Fields

	private Integer billId;
	private Date billDate;
	private Integer patientId;
	private Double doctorfees;
	private Double medicinesamount;
	private Integer bedId;
	private Long numberOfDaysAdmitted;
	private Double bedfeesFees;
	private Double totalamount;

	// Constructors

	/** default constructor */
	public AbstractBillingdetails() {
	}

	/** full constructor */
	public AbstractBillingdetails(Date billDate, Integer patientId,
			Double doctorfees, Double medicinesamount, Integer bedId,
			Long numberOfDaysAdmitted, Double bedfeesFees, Double totalamount) {
		this.billDate = billDate;
		this.patientId = patientId;
		this.doctorfees = doctorfees;
		this.medicinesamount = medicinesamount;
		this.bedId = bedId;
		this.numberOfDaysAdmitted = numberOfDaysAdmitted;
		this.bedfeesFees = bedfeesFees;
		this.totalamount = totalamount;
	}

	// Property accessors

	public Integer getBillId() {
		return this.billId;
	}

	public void setBillId(Integer billId) {
		this.billId = billId;
	}

	public Date getBillDate() {
		return this.billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public Integer getPatientId() {
		return this.patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Double getDoctorfees() {
		return this.doctorfees;
	}

	public void setDoctorfees(Double doctorfees) {
		this.doctorfees = doctorfees;
	}

	public Double getMedicinesamount() {
		return this.medicinesamount;
	}

	public void setMedicinesamount(Double medicinesamount) {
		this.medicinesamount = medicinesamount;
	}

	public Integer getBedId() {
		return this.bedId;
	}

	public void setBedId(Integer bedId) {
		this.bedId = bedId;
	}

	public Long getNumberOfDaysAdmitted() {
		return this.numberOfDaysAdmitted;
	}

	public void setNumberOfDaysAdmitted(Long numberOfDaysAdmitted) {
		this.numberOfDaysAdmitted = numberOfDaysAdmitted;
	}

	public Double getBedfeesFees() {
		return this.bedfeesFees;
	}

	public void setBedfeesFees(Double bedfeesFees) {
		this.bedfeesFees = bedfeesFees;
	}

	public Double getTotalamount() {
		return this.totalamount;
	}

	public void setTotalamount(Double totalamount) {
		this.totalamount = totalamount;
	}

}