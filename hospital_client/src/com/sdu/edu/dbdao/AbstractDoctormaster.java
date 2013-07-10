package com.sdu.edu.dbdao;

/**
 * AbstractDoctormaster entity provides the base persistence definition of the
 * Doctormaster entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractDoctormaster implements java.io.Serializable {

	// Fields

	private Integer doctorId;
	private String doctorName;
	private String doctorGender;
	private Double doctorFees;
	private String doctorSpeciality;
	private Long doctorPhonenumber;
	private String doctorEmail;

	// Constructors

	/** default constructor */
	public AbstractDoctormaster() {
	}

	/** full constructor */
	public AbstractDoctormaster(String doctorName, String doctorGender,
			Double doctorFees, String doctorSpeciality, Long doctorPhonenumber,
			String doctorEmail) {
		this.doctorName = doctorName;
		this.doctorGender = doctorGender;
		this.doctorFees = doctorFees;
		this.doctorSpeciality = doctorSpeciality;
		this.doctorPhonenumber = doctorPhonenumber;
		this.doctorEmail = doctorEmail;
	}

	// Property accessors

	public Integer getDoctorId() {
		return this.doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return this.doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDoctorGender() {
		return this.doctorGender;
	}

	public void setDoctorGender(String doctorGender) {
		this.doctorGender = doctorGender;
	}

	public Double getDoctorFees() {
		return this.doctorFees;
	}

	public void setDoctorFees(Double doctorFees) {
		this.doctorFees = doctorFees;
	}

	public String getDoctorSpeciality() {
		return this.doctorSpeciality;
	}

	public void setDoctorSpeciality(String doctorSpeciality) {
		this.doctorSpeciality = doctorSpeciality;
	}

	public Long getDoctorPhonenumber() {
		return this.doctorPhonenumber;
	}

	public void setDoctorPhonenumber(Long doctorPhonenumber) {
		this.doctorPhonenumber = doctorPhonenumber;
	}

	public String getDoctorEmail() {
		return this.doctorEmail;
	}

	public void setDoctorEmail(String doctorEmail) {
		this.doctorEmail = doctorEmail;
	}

}