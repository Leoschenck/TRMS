package com.revature.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daoImpls.FormDaoImpl;

/**
 * <h1>Team KLLJ - Tuition Reimbursement Management System (TRMS) Project 1</h1>
 * The purpose of TRMS is to provide a system that encourages quality knowledge growth
 * relevant to an individual’s expertise.  This program was created to address the
 * problems present in the current system, to provide the best user experience possible,
 * and to provide a more streamlined process for everyone involved.
 * <p>
 * Servlet implementation class FormApprovalServlet
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/approval.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession s = request.getSession(false);
		FormDaoImpl fdi = new FormDaoImpl();
		ObjectMapper mapper = new ObjectMapper();
		int status = 0;
		if (s != null) {
			XhrApprovalObject xhrApproval = mapper.readValue(request.getInputStream(), XhrApprovalObject.class);
		// TODO do approvals / denials, set strings
			//sets the status to the correct status depending on the relationship between the logged in user and the form he chose to interact with.
			if (xhrApproval.getApproved() == 1) {
				try {
				//If the state is 0 and the user is the supervisor, we set the status to one or two, depending on if he also is the depthead
				if (xhrApproval.getStatus() == 0 && fdi.isSupervisor(xhrApproval.getFormId(), (int) s.getAttribute("userId"))) 
				{
					status = (fdi.isDeptHead(xhrApproval.getFormId(), (int) s.getAttribute("userId")))?2:1;	
				}else if (xhrApproval.getStatus() == 1 && fdi.isDeptHead(xhrApproval.getFormId(), (int) s.getAttribute("userId"))) 
				{
					status = 2;
				} else if(xhrApproval.getStatus() == 2 && (int)s.getAttribute("isBenco") == 1) {	
					status = 3;
				}
				fdi.setStatus(xhrApproval.getFormId(), status);	
			} catch (Exception e) {
				// TODO: handle exception
			}
			}
			//TODO insert other input situations like question, denial and reimbursement change
		} else {
			response.sendRedirect("/TRMS/login");
		}
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