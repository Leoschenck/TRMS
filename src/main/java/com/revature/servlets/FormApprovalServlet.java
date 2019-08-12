package com.revature.servlets;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Form;
import com.revature.beans.User;
import com.revature.daoImpls.FormDaoImpl;
import com.revature.daoImpls.NotificationDaoImpl;
import com.revature.daoImpls.UserDaoImpl;

/**
 * <h1>Team KLLJ - Tuition Reimbursement Management System (TRMS) Project 1</h1>
 * The purpose of TRMS is to provide a system that encourages quality knowledge
 * growth relevant to an individual’s expertise. This program was created to
 * address the problems present in the current system, to provide the best user
 * experience possible, and to provide a more streamlined process for everyone
 * involved.
 * <p>
 * Servlet implementation class FormApprovalServlet
 * 
 * @author Justin Hua, Kyle Kolstad, Leonardo Schenck, Levi Applebaum
 * @version 1.0
 * 
 */
public class FormApprovalServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/approval.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession s = request.getSession(false);
		FormDaoImpl fdi = new FormDaoImpl();
		UserDaoImpl udi = new UserDaoImpl();
		NotificationDaoImpl ndi = new NotificationDaoImpl();
		ObjectMapper mapper = new ObjectMapper();
		XhrApprovalObject xhrApproval = mapper.readValue(request.getInputStream(), XhrApprovalObject.class);
		int status = 0;
		if (s != null) {
			status = xhrApproval.getStatus();

			// TODO insert other input situations like question, denial and reimbursement
			// change
			// If the state is 0 and the user is the supervisor, we set the status to one or
			// two, depending on if he also is the depthead
			try {
				if (status == 0 && fdi.isSupervisor(xhrApproval.getFormId(), (int) s.getAttribute("userId"))) {
					status = (fdi.isDeptHead(xhrApproval.getFormId(), (int) s.getAttribute("userId"))) ? 2 : 1;

				} else if (status == 1 && fdi.isDeptHead(xhrApproval.getFormId(), (int) s.getAttribute("userId"))) {
					status = 2;
				} else if (status == 2 && (int) s.getAttribute("isBenco") == 1) {

					status = 3;
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				fdi.setStatus(xhrApproval.getFormId(), status);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if ((status = xhrApproval.getApproved()) == -1) { // Denied the Form!

			try { // lmao don't even try to understand this mess! jkjk - it get's the userid of
					// the forms owner and updates his remaining balance to blurg I HATE JAVASCRIPT
					// AND EVERYTHING HERE AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA
				int userIdOfForm = udi.getUserIdByFormId(xhrApproval.getFormId());
				ndi.createNotification(xhrApproval.getFormId(),
						"Your Application was denied. \n\n" + xhrApproval.getAttachedReasoning());
				double newAmount = calculateNewAmount(fdi.getFormBy(xhrApproval.getFormId()),
						udi.getUserById(userIdOfForm));
				udi.changeReimbursementAmount(newAmount, userIdOfForm);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				fdi.setStatus(xhrApproval.getFormId(), status);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (xhrApproval.getApproved() == 0) { // Add a Question! Display them in open forms? where status != -1
														// && 3 && 4
			try {
				ndi.createNotification(xhrApproval.getFormId(), "Open Question about your form "
						+ xhrApproval.getFormId() + "\n\n" + xhrApproval.getAttachedReasoning());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else{response.sendRedirect("/TRMS/login");}}

	private double calculateNewAmount(Form f, User u) {
		double newAmount = u.getRmnReimbursement();
		switch (f.getTypeOfEvent()) {
		case "1":
			newAmount = 0.8 * f.getCost();
			break;
		case "2":
			newAmount = 0.6 * f.getCost();
			break;
		case "3":
			newAmount = 0.75 * f.getCost();
			break;// document.getElementById("reimbamtEst").value = parseFloat(
		case "4":
			newAmount = f.getCost();
			break;

		case "5":
			newAmount = 0.9 * f.getCost();
			break;
		case "6":
			newAmount = 0.3 * f.getCost();
			break;
		}
		return newAmount + u.getRmnReimbursement();
	}

	/*
	 * private String stringifyJson(ServletInputStream inStream) throws IOException{
	 * StringBuffer sb = new StringBuffer(); int b = '0'; //sb.append("{\"");
	 * while((b = inStream.read()) != -1) {
	 * 
	 * switch((char)b) { case('='): sb.append("\":\""); break; case('&'):
	 * sb.append("\",\""); break; case('+'): sb.append(" "); break; default:
	 * sb.append((char)b); }
	 * 
	 * sb.append((char)b); } //sb.append("\"}"); System.out.println(sb); return
	 * sb.toString(); }
	 */
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
class XhrApprovalObject {
	
	//Private data variables.
	private String attachedReasoning;
	private int formId, approved, subsAmt, status;

	/**
	 * Default Constructor.
	 */
	public XhrApprovalObject() {
		super();
	}
	
	/**
	 * This method creates an XhrApprovalObject object and initializes the data variables.
	 * @param formId
	 * @param approved
	 * @param attachedReasoning
	 * @param newReimbursementAmount
	 * @param status
	 */
	public XhrApprovalObject(int formId, int approved, String attachedReasoning, int newReimbursementAmount,
			int status) {
		super();
		this.formId = formId;
		this.approved = approved;
		this.attachedReasoning = attachedReasoning;
		this.subsAmt = newReimbursementAmount;
		this.status = status;
	}

	//Getter and setter methods.

	public int getSubsAmt() {
		return subsAmt;
	}

	public void setSubsAmt(int subsAmt) {
		this.subsAmt = subsAmt;
	}

	public int getFormId() {
		return formId;
	}

	public void setFormId(int formId) {
		this.formId = formId;
	}

	public int getApproved() {
		return approved;
	}

	public void setApproved(int approved) {
		this.approved = approved;
	}

	public String getAttachedReasoning() {
		return attachedReasoning;
	}

	public void setAttachedReasoning(String attachedReasoning) {
		this.attachedReasoning = attachedReasoning;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}