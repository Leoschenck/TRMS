package com.revature.beans;

public class User {

	private String userName;
	private String password;
	private int id;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	private int rmnReimbursement;
	private int reportsTo;

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
				+ rmnReimbursement + ", reportsTo=" + reportsTo + "]";
	}
	public User() {
		super();
	}
	public User(String userName, String password, int id, String firstName, String lastName, String phoneNumber,
			String email, int rmnReimbursement, int reportsTo) {
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
	public int getRmnReimbursement() {
		return rmnReimbursement;
	}
	public void setRmnReimbursement(int rmnReimbursement) {
		this.rmnReimbursement = rmnReimbursement;
	}
	public int getReportsTo() {
		return reportsTo;
	}
	public void setReportsTo(int reportsTo) {
		this.reportsTo = reportsTo;
	}
	
}
