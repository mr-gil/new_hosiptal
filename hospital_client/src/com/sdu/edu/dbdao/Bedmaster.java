package com.sdu.edu.dbdao;

/**
 * Bedmaster entity. @author MyEclipse Persistence Tools
 */
public class Bedmaster extends AbstractBedmaster implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Bedmaster() {
	}

	/** full constructor */
	public Bedmaster(String bedClass, Double bedPrice, String bedAvailability) {
		super(bedClass, bedPrice, bedAvailability);
	}

}
