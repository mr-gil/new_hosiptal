package com.sdu.edu.dbdao;

/**
 * ImportantPhonenumbers entity. @author MyEclipse Persistence Tools
 */
public class ImportantPhonenumbers extends AbstractImportantPhonenumbers
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public ImportantPhonenumbers() {
	}

	/** minimal constructor */
	public ImportantPhonenumbers(String personName) {
		super(personName);
	}

	/** full constructor */
	public ImportantPhonenumbers(String personName, Integer phonenumber,
			String department) {
		super(personName, phonenumber, department);
	}

}
