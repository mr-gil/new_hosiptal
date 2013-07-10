package com.sdu.edu.dbdao;

/**
 * Loginmaster entity. @author MyEclipse Persistence Tools
 */
public class Loginmaster extends AbstractLoginmaster implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public Loginmaster() {
	}

	/** full constructor */
	public Loginmaster(String username, String password, String type) {
		super(username, password, type);
	}

}
