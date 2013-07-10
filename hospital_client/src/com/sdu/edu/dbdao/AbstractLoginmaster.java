package com.sdu.edu.dbdao;

/**
 * AbstractLoginmaster entity provides the base persistence definition of the
 * Loginmaster entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractLoginmaster implements java.io.Serializable {

	// Fields

	private String username;
	private String password;
	private String type;

	// Constructors

	/** default constructor */
	public AbstractLoginmaster() {
	}

	/** full constructor */
	public AbstractLoginmaster(String username, String password, String type) {
		this.username = username;
		this.password = password;
		this.type = type;
	}

	// Property accessors

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}