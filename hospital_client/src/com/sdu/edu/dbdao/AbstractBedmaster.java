package com.sdu.edu.dbdao;

/**
 * AbstractBedmaster entity provides the base persistence definition of the
 * Bedmaster entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractBedmaster implements java.io.Serializable {

	// Fields

	private Integer bedId;
	private String bedClass;
	private Double bedPrice;
	private String bedAvailability;

	// Constructors

	/** default constructor */
	public AbstractBedmaster() {
	}

	/** full constructor */
	public AbstractBedmaster(String bedClass, Double bedPrice,
			String bedAvailability) {
		this.bedClass = bedClass;
		this.bedPrice = bedPrice;
		this.bedAvailability = bedAvailability;
	}

	// Property accessors

	public Integer getBedId() {
		return this.bedId;
	}

	public void setBedId(Integer bedId) {
		this.bedId = bedId;
	}

	public String getBedClass() {
		return this.bedClass;
	}

	public void setBedClass(String bedClass) {
		this.bedClass = bedClass;
	}

	public Double getBedPrice() {
		return this.bedPrice;
	}

	public void setBedPrice(Double bedPrice) {
		this.bedPrice = bedPrice;
	}

	public String getBedAvailability() {
		return this.bedAvailability;
	}

	public void setBedAvailability(String bedAvailability) {
		this.bedAvailability = bedAvailability;
	}

}