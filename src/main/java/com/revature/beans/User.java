package com.revature.beans;

/**
 * <h1>Team KLLJ - Tuition Reimbursement Management System (TRMS) Project 1</h1>
 * The purpose of TRMS is to provide a system that encourages quality knowledge growth
 * relevant to an individual’s expertise.  This program was created to address the
 * problems present in the current system, to provide the best user experience possible,
 * and to provide a more streamlined process for everyone involved.
 * <p>
 * This class is set up as an encapsulated class to store data that can be accessed and
 * manipulated through its setter and getter methods.
 * @author Justin Hua, Kyle Kolstad, Leonardo Schenck, Levi Applebaum
 * @version 1.0
 * 
 */
public class User {

	//Private data variables.
	private int id, reportsTo;
	private double rmnReimbursement;
	private String firstName, lastName, phoneNumber, email, userName, password;

	/**
	 * Default Constructor.
	 */
	public User() {
		super();
	}

	/**
	 * Constructor used to create a User and to initialize the data variables.
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param phoneNumber
	 * @param email
	 * @param rmnReimbursement
	 * @param reportsTo
	 * @param userName
	 * @param password
	 */
	public User(int id, String firstName, String lastName, String phoneNumber,
			String email, double rmnReimbursement, int reportsTo, String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.rmnReimbursement = rmnReimbursement;
		this.reportsTo = reportsTo;
	}
	
	//Getter and setter methods.
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public double getRmnReimbursement() {
		return rmnReimbursement;
	}
	
	public void setRmnReimbursement(double rmnReimbursement) {
		this.rmnReimbursement = rmnReimbursement;
	}
	
	public int getReportsTo() {
		return reportsTo;
	}
	
	public void setReportsTo(int reportsTo) {
		this.reportsTo = reportsTo;
	}
	
	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", id=" + id + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", phoneNumber=" + phoneNumber + ", email=" + email + ", rmnReimbursement="
				+ rmnReimbursement + ", reportsTo=" + reportsTo + "]\n";
	}
}