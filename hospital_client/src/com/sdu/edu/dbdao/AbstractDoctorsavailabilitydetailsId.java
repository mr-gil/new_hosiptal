package com.sdu.edu.dbdao;

/**
 * AbstractDoctorsavailabilitydetailsId entity provides the base persistence
 * definition of the DoctorsavailabilitydetailsId entity. @author MyEclipse
 * Persistence Tools
 */

public abstract class AbstractDoctorsavailabilitydetailsId implements
		java.io.Serializable {

	// Fields

	private Integer id;
	private Integer doctorId;

	// Constructors

	/** default constructor */
	public AbstractDoctorsavailabilitydetailsId() {
	}

	/** full constructor */
	public AbstractDoctorsavailabilitydetailsId(Integer id, Integer doctorId) {
		this.id = id;
		this.doctorId = doctorId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDoctorId() {
		return this.doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AbstractDoctorsavailabilitydetailsId))
			return false;
		AbstractDoctorsavailabilitydetailsId castOther = (AbstractDoctorsavailabilitydetailsId) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getDoctorId() == castOther.getDoctorId()) || (this
						.getDoctorId() != null
						&& castOther.getDoctorId() != null && this
						.getDoctorId().equals(castOther.getDoctorId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getDoctorId() == null ? 0 : this.getDoctorId().hashCode());
		return result;
	}

}