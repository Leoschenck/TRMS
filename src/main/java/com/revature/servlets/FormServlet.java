package com.revature.servlets;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
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
		System.out.println("in dopost of Form (yey we did it!)");
		ObjectMapper mapper = new ObjectMapper();
		String input = fixJson(request.getInputStream());
		InputStream inStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
		PreparedForm pf = mapper.readValue(inStream, PreparedForm.class);
		System.out.println(pf);
		HttpSession s = request.getSession(false);
		new FormDaoImpl().createForm(pf.getCourseStart(), pf.getLocation(), pf.getDescription(), pf.getCost(), pf.getGradingFormat(), pf.getTypeOfEvent(), pf.getWorkRelatedJustification(), pf.getWorkTimeMissed(), pf.getLinkToFiles(), 2, pf.getDeptId());
		response.sendRedirect("/TRMS/home");
	}
	
	private String fixJson(ServletInputStream inStream) throws IOException{
		StringBuffer sb = new StringBuffer();
		int b = '0';
		sb.append("{\"");
		while((b = inStream.read()) != -1) {
			switch((char)b) {
			case('='): sb.append("\":\""); break;
			case('&'): sb.append("\",\""); break;
			case('+'): sb.append(" "); break;
			default: sb.append((char)b);
			}
			
		}
		sb.append("\"}");
		System.out.println(sb);
		return sb.toString();
	}

}

class PreparedForm {

	private String typeOfEvent;
	private String description;
	private Date courseStart;
	private String location;
	private double cost;
	private String gradingFormat;
	private String workRelatedJustification;
	private double WorkTimeMissed;
	private int deptId;
	private String linkToFiles;
	public String getTypeOfEvent() {
		return typeOfEvent;
	}
	public void setTypeOfEvent(String typeOfEvent) {
		this.typeOfEvent = typeOfEvent;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public int getDeptId() {
		return deptId;
	}
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	public String getLinkToFiles() {
		return linkToFiles;
	}
	public void setLinkToFiles(String linkToFiles) {
		this.linkToFiles = linkToFiles;
	}
	public PreparedForm(String typeOfEvent, String description, Date courseStart, String location, double cost,
			String gradingFormat, String workRelatedJustification, double workTimeMissed, int deptId,
			String linkToFiles) {
		super();
		this.typeOfEvent = typeOfEvent;
		this.description = description;
		this.courseStart = courseStart;
		this.location = location;
		this.cost = cost;
		this.gradingFormat = gradingFormat;
		this.workRelatedJustification = workRelatedJustification;
		WorkTimeMissed = workTimeMissed;
		this.deptId = deptId;
		this.linkToFiles = linkToFiles;
	}
	@Override
	public String toString() {
		return "PreparedForm [typeOfEvent=" + typeOfEvent + ", description=" + description + ", courseStart="
				+ courseStart + ", location=" + location + ", cost=" + cost + ", gradingFormat=" + gradingFormat
				+ ", workRelatedJustification=" + workRelatedJustification + ", WorkTimeMissed=" + WorkTimeMissed
				+ ", deptId=" + deptId + ", linkToFiles=" + linkToFiles + "]";
	}
	public PreparedForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}