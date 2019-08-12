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
public class Form {

	//Private data variables.
	private double cost, WorkTimeMissed;
	private Date openDateTime, courseStart;
	private int id, status, employeeId, deptId;
	private String location, description, reasonForExceedingMax, gradingFormat, typeOfEvent, workRelatedJustification, deniedReason, openQuestions, linkToFiles;

	/**
	 * Default Constructor.
	 */
	public Form() {
		super();
	}
	
	/**
	 * Constructor used to create a Form and to initialize the data variables.
	 * @param id
	 * @param openDateTime
	 * @param courseStart
	 * @param location
	 * @param description
	 * @param cost
	 * @param reasonForExceedingMax
	 * @param gradingFormat
	 * @param typeOfEvent
	 * @param workRelatedJustification
	 * @param status
	 * @param deniedReason
	 * @param openQuestions
	 * @param workTimeMissed
	 * @param linkToFiles
	 * @param employeeId
	 * @param deptId
	 */
	public Form(int id, Date openDateTime, Date courseStart, String location, String description, double cost,
			String reasonForExceedingMax, String gradingFormat, String typeOfEvent, String workRelatedJustification,
			int status, String deniedReason, String openQuestions, double workTimeMissed, String linkToFiles,
			int employeeId, int deptId) {
		super();
		this.id = id;
		this.openDateTime = openDateTime;
		this.courseStart = courseStart;
		this.location = location;
		this.description = description;
		this.cost = cost;
		this.reasonForExceedingMax = reasonForExceedingMax;
		this.gradingFormat = gradingFormat;
		this.typeOfEvent = typeOfEvent;
		this.workRelatedJustification = workRelatedJustification;
		this.status = status;
		this.deniedReason = deniedReason;
		this.openQuestions = openQuestions;
		WorkTimeMissed = workTimeMissed;
		this.linkToFiles = linkToFiles;
		this.employeeId = employeeId;
		this.deptId = deptId;
	}
	
	//Getter and setter methods.
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Date getOpenDateTime() {
		return openDateTime;
	}
	
	public void setOpenDateTime(Date openDateTime) {
		this.openDateTime = openDateTime;
	}
	
	public Date getCourseStart() {
		return courseStart;
	}
	
	public void setCourseStart(Date courseStart) {
		this.courseStart = courseStart;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public double getCost() {
		return cost;
	}
	
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public String getReasonForExceedingMax() {
		return reasonForExceedingMax;
	}
	
	public void setReasonForExceedingMax(String reasonForExceedingMax) {
		this.reasonForExceedingMax = reasonForExceedingMax;
	}
	
	public String getGradingFormat() {
		return gradingFormat;
	}
	
	public void setGradingFormat(String gradingFormat) {
		this.gradingFormat = gradingFormat;
	}
	
	public String getTypeOfEvent() {
		return typeOfEvent;
	}
	
	public void setTypeOfEvent(String typeOfEvent) {
		this.typeOfEvent = typeOfEvent;
	}
	
	public String getWorkRelatedJustification() {
		return workRelatedJustification;
	}
	
	public void setWorkRelatedJustification(String workRelatedJustification) {
		this.workRelatedJustification = workRelatedJustification;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getDeniedReason() {
		return deniedReason;
	}
	
	public void setDeniedReason(String deniedReason) {
		this.deniedReason = deniedReason;
	}
	
	public String getOpenQuestions() {
		return openQuestions;
	}
	
	public void setOpenQuestions(String openQuestions) {
		this.openQuestions = openQuestions;
	}
	
	public double getWorkTimeMissed() {
		return WorkTimeMissed;
	}
	
	public void setWorkTimeMissed(double workTimeMissed) {
		WorkTimeMissed = workTimeMissed;
	}
	
	public String getLinkToFiles() {
		return linkToFiles;
	}
	
	public void setLinkToFiles(String linkToFiles) {
		this.linkToFiles = linkToFiles;
	}
	
	public int getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
	public int getDeptId() {
		return deptId;
	}
	
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	
	@Override
	public String toString() {
		return "Form [id=" + id + ", openDateTime=" + openDateTime + ", courseStart=" + courseStart + ", location="
				+ location + ", description=" + description + ", cost=" + cost + ", reasonForExceedingMax="
				+ reasonForExceedingMax + ", gradingFormat=" + gradingFormat + ", typeOfEvent=" + typeOfEvent
				+ ", workRelatedJustification=" + workRelatedJustification + ", status=" + status + ", deniedReason="
				+ deniedReason + ", openQuestions=" + openQuestions + ", WorkTimeMissed=" + WorkTimeMissed
				+ ", linkToFiles=" + linkToFiles + ", employeeId=" + employeeId + ", deptId=" + deptId + "]";
	}
}