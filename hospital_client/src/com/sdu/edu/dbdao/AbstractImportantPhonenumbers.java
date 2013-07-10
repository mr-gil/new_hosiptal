package com.sdu.edu.dbdao;

/**
 * AbstractImportantPhonenumbers entity provides the base persistence definition
 * of the ImportantPhonenumbers entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractImportantPhonenumbers implements
		java.io.Serializable {

	// Fields

	private String personName;
	private Integer phonenumber;
	private String department;

	// Constructors

	/** default constructor */
	public AbstractImportantPhonenumbers() {
	}

	/** minimal constructor */
	public AbstractImportantPhonenumbers(String personName) {
		this.personName = personName;
	}

	/** full constructor */
	public AbstractImportantPhonenumbers(String personName,
			Integer phonenumber, String department) {
		this.personName = personName;
		this.phonenumber = phonenumber;
		this.department = department;
	}

	// Property accessors

	public String getPersonName() {
		return this.personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public Integer getPhonenumber() {
		return this.phonenumber;
	}

	public void setPhonenumber(Integer phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

}