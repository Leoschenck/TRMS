package com.revature.servlets;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.User;
import com.revature.daoImpls.FormDaoImpl;
import com.revature.daoImpls.UserDaoImpl;

/**
 * <h1>Team KLLJ - Tuition Reimbursement Management System (TRMS) Project 1</h1>
 * The purpose of TRMS is to provide a system that encourages quality knowledge growth
 * relevant to an individualís expertise.  This program was created to address the
 * problems present in the current system, to provide the best user experience possible,
 * and to provide a more streamlined process for everyone involved.
 * <p>
 * Servlet implementation class FormServlet
 * @author Justin Hua, Kyle Kolstad, Leonardo Schenck, Levi Applebaum
 * @version 1.0
 * 
 */
public class FormServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession s = req.getSession(false);
		if (s != null) {
			req.getRequestDispatcher("/form.html").include(req, resp); // TODO homepage
		} else {
			resp.sendRedirect("/TRMS/login");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		UserDaoImpl udi = new UserDaoImpl();
		String input = fixJson(request.getInputStream());
		InputStream inStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
		PreparedForm pf = mapper.readValue(inStream, PreparedForm.class);
		HttpSession s = request.getSession(false);
		try {
		new FormDaoImpl().createForm(pf.getCourseStart(), pf.getLocation(), pf.getDescription(), pf.getCost(),
				pf.getGradingFormat(), pf.getTypeOfEvent(), pf.getWorkRelatedJustification(), pf.getWorkTimeMissed(),
				pf.getLinkToFiles(), (int) s.getAttribute("userId"), pf.getDeptId());
		response.sendRedirect("/TRMS/home");
		double newAmount;

			newAmount = calculateNewAmount(pf, udi.getUserById((int) s.getAttribute("userId")));
			udi.changeReimbursementAmount(newAmount, (int)s.getAttribute("userId"));
			
			//TODO notification
		} catch (SQLException e) {
			//e.printStackTrace();
		}
	}

	/**
	 * This method will calculate the new reimbursement amount
	 * @param pf
	 * @param u
	 * @return New Amount
	 */
	private double calculateNewAmount(PreparedForm pf, User u) {
		double newAmount = 0;
		switch (pf.getTypeOfEvent()) {
		case "1":
			newAmount = 0.8 * pf.getCost();
			break;
		case "2":
			newAmount = 0.6 * pf.getCost();
			break;
		case "3":
			newAmount = 0.75 * pf.getCost();
			break;
		case "4":
			newAmount = pf.getCost();
			break;
		case "5":
			newAmount = 0.9 * pf.getCost();
			break;
		case "6":
			newAmount = 0.3 * pf.getCost();
			break;
		}
		return u.getRmnReimbursement() - newAmount;
	}

	/**
	 * This Method is used to edit and fix a JSON String object and return the resulting String
	 * @param inStream
	 * @return String Object
	 * @throws IOException
	 */
	private String fixJson(ServletInputStream inStream) throws IOException {
		StringBuffer sb = new StringBuffer();
		int b = '0';
		sb.append("{\"");
		while ((b = inStream.read()) != -1) {
			switch ((char) b) {
			case ('='):
				sb.append("\":\"");
				break;
			case ('&'):
				sb.append("\",\"");
				break;
			case ('+'):
				sb.append(" ");
				break;
			default:
				sb.append((char) b);
			}
		}
		sb.append("\"}");
		return sb.toString();
	}
}

/**
 * <h1>Team KLLJ - Tuition Reimbursement Management System (TRMS) Project 1</h1>
 * The purpose of TRMS is to provide a system that encourages quality knowledge growth
 * relevant to an individualís expertise.  This program was created to address the
 * problems present in the current system, to provide the best user experience possible,
 * and to provide a more streamlined process for everyone involved.
 * <p>
 * This class is set up to store data that can be accessed and
 * manipulated through its setter and getter methods.
 * @author Justin Hua, Kyle Kolstad, Leonardo Schenck, Levi Applebaum
 * @version 1.0
 * 
 */
class PreparedForm {

	//Private data variables.
	private int deptId;
	private Date courseStart;
	private double cost, WorkTimeMissed;
	private String typeOfEvent, description, location, gradingFormat, workRelatedJustification, linkToFiles;

	/**
	 * Default Constructor.
	 */
	public PreparedForm() {
		super();
	}
	
	/**
	 * This method creates a PreparedForm object and initializes the data variables.
	 * @param typeOfEvent
	 * @param description
	 * @param courseStart
	 * @param location
	 * @param cost
	 * @param gradingFormat
	 * @param workRelatedJustification
	 * @param workTimeMissed
	 * @param deptId
	 * @param linkToFiles
	 */
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
	
	//Getter and setter methods.
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

	@Override
	public String toString() {
		return "PreparedForm [typeOfEvent=" + typeOfEvent + ", description=" + description + ", courseStart="
				+ courseStart + ", location=" + location + ", cost=" + cost + ", gradingFormat=" + gradingFormat
				+ ", workRelatedJustification=" + workRelatedJustification + ", WorkTimeMissed=" + WorkTimeMissed
				+ ", deptId=" + deptId + ", linkToFiles=" + linkToFiles + "]";
	}
}