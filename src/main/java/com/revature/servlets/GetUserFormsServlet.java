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
 * Servlet implementation class ViewFormsServlet
 */
public class GetUserFormsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetUserFormsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession s = request.getSession(false);
		ObjectMapper om = new ObjectMapper();
		System.out.println("In getuserForms doget");
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

class FormWithDeptName {

	private int id;
	private Date openDateTime;
	private Date courseStart;
	private String location;
	private String description;
	private double cost;
	private String reasonForExceedingMax;
	private String gradingFormat;
	private String typeOfEvent;
	private String workRelatedJustification;
	private int status;
	private String deniedReason;
	private String openQuestions;
	private double WorkTimeMissed;
	private String linkToFiles;
	private int employeeId;
	private String deptName;

	public FormWithDeptName() {
		super();
	}

	public FormWithDeptName(Form form) {
		this(form.getId(), form.getOpenDateTime(), form.getCourseStart(),form.getLocation(),form.getDescription(),form.getCost(),
				form.getReasonForExceedingMax(),form.getGradingFormat(),form.getTypeOfEvent(), form.getWorkRelatedJustification(),
				form.getStatus(),form.getDeniedReason(),form.getOpenQuestions(),form.getWorkTimeMissed(),form.getLinkToFiles(), 
				form.getEmployeeId(), form.getDeptId());
	}

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

	private String getDeptName(int deptId) {
		FormDaoImpl fdi = new FormDaoImpl();
		HashMap<Integer, String> departments;
		try {
			departments = fdi.getDepartments();
			return departments.get(deptId);
		} catch (SQLException e) {
			System.out.println("something in the sql conn went wrong :s In GetUSerFormServlet");
			e.printStackTrace();
		}
		return null;
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

}
