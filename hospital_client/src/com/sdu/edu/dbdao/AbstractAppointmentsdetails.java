package com.sdu.edu.dbdao;

import java.sql.Time;
import java.util.Date;

/**
 * AbstractAppointmentsdetails entity provides the base persistence definition
 * of the Appointmentsdetails entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractAppointmentsdetails implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer patientId;
	private Integer doctorId;
	private Date date;
	private Time time;
	private String ava;

	// Constructors

	/** default constructor */
	public AbstractAppointmentsdetails() {
	}

	/** full constructor */
	public AbstractAppointmentsdetails(Integer patientId, Integer doctorId,
			Date date, Time time, String ava) {
		this.patientId = patientId;
		this.doctorId = doctorId;
		this.date = date;
		this.time = time;
		this.ava = ava;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPatientId() {
		return this.patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getDoctorId() {
		return this.doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTime() {
		return this.time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public String getAva() {
		return this.ava;
	}

	public void setAva(String ava) {
		this.ava = ava;
	}

}