package com.revature.servlets;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daoImpls.FormDaoImpl;

/**
 * Servlet implementation class FormServlet
 */
public class FormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		PreparedForm pf = mapper.readValue(request.getInputStream(), PreparedForm.class);
		System.out.println(pf);
		HttpSession s = request.getSession(false);
		new FormDaoImpl().createForm(pf.getCourseStart(), pf.getLocation(), pf.getDescription(), pf.getCost(), pf.getGradingFormat(), pf.getTypeOfEvent(), pf.getWorkRelatedJustification(), pf.getWorkTimeMissed(), pf.getLinkToFiles(), 2, pf.getDeptId());
		
	}

}

class PreparedForm {

	private Date courseStart;
	private String location;
	private String description;
	private double cost;
	private String gradingFormat;
	private String typeOfEvent;
	private String workRelatedJustification;
	private double WorkTimeMissed;
	private String linkToFiles;
	private int deptId;

	public PreparedForm() {}

	public PreparedForm(Date courseStart, String location, String description, double cost, String gradingFormat,
			String typeOfEvent, String workRelatedJustification, double workTimeMissed, String linkToFiles,
			int deptId) {
		super();
		this.courseStart = courseStart;
		this.location = location;
		this.description = description;
		this.cost = cost;
		this.gradingFormat = gradingFormat;
		this.typeOfEvent = typeOfEvent;
		this.workRelatedJustification = workRelatedJustification;
		WorkTimeMissed = workTimeMissed;
		this.linkToFiles = linkToFiles;
		this.deptId = deptId;
	}

	@Override
	public String toString() {
		return "PreparedForm [courseStart=" + courseStart + ", location=" + location + ", description=" + description
				+ ", cost=" + cost + ", gradingFormat=" + gradingFormat + ", typeOfEvent=" + typeOfEvent
				+ ", workRelatedJustification=" + workRelatedJustification + ", WorkTimeMissed=" + WorkTimeMissed
				+ ", linkToFiles=" + linkToFiles + ", deptId=" + deptId + "]";
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

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
}