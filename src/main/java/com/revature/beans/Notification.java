package com.revature.beans;

import java.sql.Date;

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
public class Notification {

	//Private data variables
	private int id, formID, checked;
	private String content;
	private Date time;
	
	/**
	 * Default Constructor
	 */
	public Notification() {
		super();
	}

	/**
	 * Constructor used to create a Notification and to initialize the data variables.
	 * @param id
	 * @param formID
	 * @param content
	 * @param time
	 * @param checked
	 */
	public Notification(int id, int formID, String content, Date time, int checked) {
		super();
		this.id = id;
		this.formID = formID;
		this.content = content;
		this.time = time;
		this.checked = checked;
	}

	//Getter and setter methods
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFormID() {
		return formID;
	}

	public void setFormID(int formID) {
		this.formID = formID;
	}

	public int getChecked() {
		return checked;
	}

	public void setChecked(int checked) {
		this.checked = checked;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Notification [id=" + id + ", formID=" + formID + ", content=" + content + ", time=" + time
				+ ", checked=" + checked + "]";
	}
}
