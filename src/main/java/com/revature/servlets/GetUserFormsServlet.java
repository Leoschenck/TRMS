package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Form;
import com.revature.daoImpls.FormDaoImpl;

/**
 * <h1>Team KLLJ - Tuition Reimbursement Management System (TRMS) Project 1</h1>
 * The purpose of TRMS is to provide a system that encourages quality knowledge growth
 * relevant to an individual’s expertise.  This program was created to address the
 * problems present in the current system, to provide the best user experience possible,
 * and to provide a more streamlined process for everyone involved.
 * <p>
 * Servlet implementation class GetDepartmentsServlet
 * @author Justin Hua, Kyle Kolstad, Leonardo Schenck, Levi Applebaum
 * @version 1.0
 * 
 */
public class GetUserFormsServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s = request.getSession(false);
		ObjectMapper om = new ObjectMapper();
		PrintWriter out = response.getWriter();
		FormDaoImpl fdi = new FormDaoImpl();
		ArrayList<Form> allForms = null;
		ArrayList<FormWithDeptName> allFormsWithDeptName = new ArrayList<FormWithDeptName>();
		if (s != null) {
			int id = (int) s.getAttribute("userId");
			try {
				allForms = fdi.getFormsByUserId(id);
				for (Form form : allForms) {
					allFormsWithDeptName.add(new FormWithDeptName(form));
				}
				om.writeValue(out, allFormsWithDeptName);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.flush();
		} else {
			response.sendRedirect("/TRMS/login");
		}
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

/**
 * <h1>Team KLLJ - Tuition Reimbursement Management System (TRMS) Project 1</h1>
 * The purpose of TRMS is to provide a system that encourages quality knowledge growth
 * relevant to an individual’s expertise.  This program was created to address the
 * problems present in the current system, to provide the best user experience possible,
 * and to provide a more streamlined process for everyone involved.
 * <p>
 * This class is set up to store data that can be accessed and
 * manipulated through its setter and getter methods.
 * @author Justin Hua, Kyle Kolstad, Leonardo Schenck, Levi Applebaum
 * @version 1.0
 * 
 */
class FormWithDeptName {

	//Private data variables.
	private int id, status, employeeId;
	private double cost, WorkTimeMissed;
	private Date openDateTime, courseStart;
	private String location, description, reasonForExceedingMax, gradingFormat, typeOfEvent, workRelatedJustification, deniedReason, openQuestions, linkToFiles, deptName;

	/**
	 * Default Constructor.
	 */
	public FormWithDeptName() {
		super();
	}

	/**
	 * This method creates an FormWithDeptName object and initializes the data variables.
	 * @param form
	 */
	public FormWithDeptName(Form form) {
		this(form.getId(), form.getOpenDateTime(), form.getCourseStart(),form.getLocation(),form.getDescription(),form.getCost(),
				form.getReasonForExceedingMax(),form.getGradingFormat(),form.getTypeOfEvent(), form.getWorkRelatedJustification(),
				form.getStatus(),form.getDeniedReason(),form.getOpenQuestions(),form.getWorkTimeMissed(),form.getLinkToFiles(), 
				form.getEmployeeId(), form.getDeptId());
	}

	/**
	 * This method creates an FormWithDeptName object and initializes the data variables.
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
	public FormWithDeptName(int id, Date openDateTime, Date courseStart, String location, String description,
			double cost, String reasonForExceedingMax, String gradingFormat, String typeOfEvent,
			String workRelatedJustification, int status, String deniedReason, String openQuestions,
			double workTimeMissed, String linkToFiles, int employeeId, int deptId) {
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
		this.deptName = getDeptName(deptId);
	}

	/**
	 * This method will use the department id as a key to return the department name as a String
	 * @param deptId
	 * @return
	 */
	private String getDeptName(int deptId) {
		FormDaoImpl fdi = new FormDaoImpl();
		HashMap<Integer, String> departments;
		try {
			departments = fdi.getDepartments();
			return departments.get(deptId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
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

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(int deptId) {
		this.deptName = getDeptName(deptId);
	}

	@Override
	public String toString() {
		return "Form [id=" + id + ", openDateTime=" + openDateTime + ", courseStart=" + courseStart + ", location="
				+ location + ", description=" + description + ", cost=" + cost + ", reasonForExceedingMax="
				+ reasonForExceedingMax + ", gradingFormat=" + gradingFormat + ", typeOfEvent=" + typeOfEvent
				+ ", workRelatedJustification=" + workRelatedJustification + ", status=" + status + ", deniedReason="
				+ deniedReason + ", openQuestions=" + openQuestions + ", WorkTimeMissed=" + WorkTimeMissed
				+ ", linkToFiles=" + linkToFiles + ", employeeId=" + employeeId + ", deptName=" + deptName + "]";
	}
}
