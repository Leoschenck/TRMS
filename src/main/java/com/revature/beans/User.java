package com.revature.beans;

public class User {

	private int id;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private double rmnReimbursement;
	private int reportsTo;
	private String userName;
	private String password;

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	@Override
	public String toString() {
		return "User [userName=" + userName + ", password=" + password + ", id=" + id + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", phoneNumber=" + phoneNumber + ", email=" + email + ", rmnReimbursement="
				+ rmnReimbursement + ", reportsTo=" + reportsTo + "]\n";
	}

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
	public User() {
		// TODO Auto-generated constructor stub
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
	
}
