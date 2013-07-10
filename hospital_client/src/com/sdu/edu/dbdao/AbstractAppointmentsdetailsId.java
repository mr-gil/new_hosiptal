package com.sdu.edu.dbdao;

/**
 * AbstractAppointmentsdetailsId entity provides the base persistence definition
 * of the AppointmentsdetailsId entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractAppointmentsdetailsId implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer patientId;

	// Constructors

	/** default constructor */
	public AbstractAppointmentsdetailsId() {
	}

	/** full constructor */
	public AbstractAppointmentsdetailsId(Integer id, Integer patientId) {
		this.id = id;
		this.patientId = patientId;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractAppointmentsdetailsId))
			return false;
		AbstractAppointmentsdetailsId castOther = (AbstractAppointmentsdetailsId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getPatientId() == castOther.getPatientId()) || (this
						.getPatientId() != null
						&& castOther.getPatientId() != null && this
						.getPatientId().equals(castOther.getPatientId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getPatientId() == null ? 0 : this.getPatientId().hashCode());
		return result;
	}

}