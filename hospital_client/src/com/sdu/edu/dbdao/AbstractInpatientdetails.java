package com.sdu.edu.dbdao;

import java.util.Date;

/**
 * AbstractInpatientdetails entity provides the base persistence definition of
 * the Inpatientdetails entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractInpatientdetails implements java.io.Serializable {

	// Fields

	private Integer patientId;
	private Date dateofadmission;
	private Integer bedId;
	private Date dateOfDischarge;
	private String comments;
	private Integer doctorId;

	// Constructors

	/** default constructor */
	public AbstractInpatientdetails() {
	}

	/** minimal constructor */
	public AbstractInpatientdetails(Integer patientId) {
		this.patientId = patientId;
	}

	/** full constructor */
	public AbstractInpatientdetails(Integer patientId, Date dateofadmission,
			Integer bedId, Date dateOfDischarge, String comments,
			Integer doctorId) {
		this.patientId = patientId;
		this.dateofadmission = dateofadmission;
		this.bedId = bedId;
		this.dateOfDischarge = dateOfDischarge;
		this.comments = comments;
		this.doctorId = doctorId;
	}

	// Property accessors

	public Integer getPatientId() {
		return this.patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Date getDateofadmission() {
		return this.dateofadmission;
	}

	public void setDateofadmission(Date dateofadmission) {
		this.dateofadmission = dateofadmission;
	}

	public Integer getBedId() {
		return this.bedId;
	}

	public void setBedId(Integer bedId) {
		this.bedId = bedId;
	}

	public Date getDateOfDischarge() {
		return this.dateOfDischarge;
	}

	public void setDateOfDischarge(Date dateOfDischarge) {
		this.dateOfDischarge = dateOfDischarge;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Integer getDoctorId() {
		return this.doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

}