package com.sdu.edu.dbdao;

import java.util.Date;

/**
 * AbstractPatientmaster entity provides the base persistence definition of the
 * Patientmaster entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractPatientmaster implements java.io.Serializable {

	// Fields

	private Integer patientId;
	private String patientName;
	private String patientGender;
	private String patientEmail;
	private Date patientDateofreg;
	private Long patientPhonenumber;

	// Constructors

	/** default constructor */
	public AbstractPatientmaster() {
	}

	/** full constructor */
	public AbstractPatientmaster(String patientName, String patientGender,
			String patientEmail, Date patientDateofreg, Long patientPhonenumber) {
		this.patientName = patientName;
		this.patientGender = patientGender;
		this.patientEmail = patientEmail;
		this.patientDateofreg = patientDateofreg;
		this.patientPhonenumber = patientPhonenumber;
	}

	// Property accessors

	public Integer getPatientId() {
		return this.patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return this.patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientGender() {
		return this.patientGender;
	}

	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}

	public String getPatientEmail() {
		return this.patientEmail;
	}

	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}

	public Date getPatientDateofreg() {
		return this.patientDateofreg;
	}

	public void setPatientDateofreg(Date patientDateofreg) {
		this.patientDateofreg = patientDateofreg;
	}

	public Long getPatientPhonenumber() {
		return this.patientPhonenumber;
	}

	public void setPatientPhonenumber(Long patientPhonenumber) {
		this.patientPhonenumber = patientPhonenumber;
	}

}