package com.revature.beans;

public class Form {

	private int id;
	private String openDateTime;
	private String courseStart;
	private String location;
	private String description;
	private int cost;
	private String reasonForExceedingMax;
	private int gradingFormat;
	private String typeOfEvent;
	private String workRelatedJustification;
	private String status;
	private String deniedReason;
	private String openQuestions;
	private int WorkTimeMissed;
	private String linkToFiles;
	private int employeeId;
	private int deptId;

	public Form() {
		super();
	}
	public Form(int id, String openDateTime, String courseStart, String location, String description, int cost,
			String reasonForExceedingMax, int gradingFormat, String typeOfEvent, String workRelatedJustification,
			String status, String deniedReason, String openQuestions, int workTimeMissed, String linkToFiles,
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
	@Override
	public String toString() {
		return "Form [id=" + id + ", openDateTime=" + openDateTime + ", courseStart=" + courseStart + ", location="
				+ location + ", description=" + description + ", cost=" + cost + ", reasonForExceedingMax="
				+ reasonForExceedingMax + ", gradingFormat=" + gradingFormat + ", typeOfEvent=" + typeOfEvent
				+ ", workRelatedJustification=" + workRelatedJustification + ", status=" + status + ", deniedReason="
				+ deniedReason + ", openQuestions=" + openQuestions + ", WorkTimeMissed=" + WorkTimeMissed
				+ ", linkToFiles=" + linkToFiles + ", employeeId=" + employeeId + ", deptId=" + deptId + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOpenDateTime() {
		return openDateTime;
	}
	public void setOpenDateTime(String openDateTime) {
		this.openDateTime = openDateTime;
	}
	public String getCourseStart() {
		return courseStart;
	}
	public void setCourseStart(String courseStart) {
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
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public String getReasonForExceedingMax() {
		return reasonForExceedingMax;
	}
	public void setReasonForExceedingMax(String reasonForExceedingMax) {
		this.reasonForExceedingMax = reasonForExceedingMax;
	}
	public int getGradingFormat() {
		return gradingFormat;
	}
	public void setGradingFormat(int gradingFormat) {
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
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
	public int getWorkTimeMissed() {
		return WorkTimeMissed;
	}
	public void setWorkTimeMissed(int workTimeMissed) {
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
}
