package com.revature.Dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import com.revature.beans.Form;

/**
 * <h1>Team KLLJ - Tuition Reimbursement Management System (TRMS) Project 1</h1>
 * The purpose of TRMS is to provide a system that encourages quality knowledge growth
 * relevant to an individual’s expertise.  This program was created to address the
 * problems present in the current system, to provide the best user experience possible,
 * and to provide a more streamlined process for everyone involved.
 * <p>
 * This Interface can be implemented by a class to use its methods
 * @author Justin Hua, Kyle Kolstad, Leonardo Schenck, Levi Applebaum
 * @version 1.0
 * 
 */
public interface FormDao {

	/**
	 * This method can be used to create a Form
	 * @param courseStart
	 * @param location
	 * @param description
	 * @param cost
	 * @param gradingFormat
	 * @param typeOfEvent
	 * @param workRelatedJustification
	 * @param workTimeMissed
	 * @param linkToFiles
	 * @param employeeId
	 * @param deptId
	 * @throws SQLException
	 */
	public void createForm(Date courseStart, String location, String description, double cost, String gradingFormat,
			String typeOfEvent, String workRelatedJustification, double workTimeMissed, String linkToFiles,
			int employeeId, int deptId) throws SQLException;
	
	/**
	 * This method will return all the forms that belong to a specified user
	 * @param employeeId
	 * @return Form ArrayList
	 * @throws SQLException
	 */
	public ArrayList<Form> getFormsByUserId(int employeeId) throws SQLException;
	
	/**
	 * This method will check to see if a user is a supervisor of another user who filled out the form
	 * @param formId
	 * @param userId
	 * @return Form ArrayList
	 * @throws SQLException
	 */
	public boolean isSupervisor(int formId, int userId) throws SQLException;
	
	/**
	 * This method will check to see if a user is the department head of another user who filled out the form
	 * @param formId
	 * @param userId
	 * @return boolean value
	 * @throws SQLException
	 */
	public boolean isDeptHead(int formId, int userId) throws SQLException;
	
	/**
	 * This method will return any forms that need to be approved
	 * @param employeeId
	 * @return boolean value
	 * @throws SQLException
	 */
	public ArrayList<Form> getApprovableFormsByUserId(int employeeId) throws SQLException;
	
	/**
	 * This method checks to see if a user has any forms that need to be approved
	 * @param userId
	 * @return boolean value
	 * @throws SQLException
	 */
	public boolean hasApprovableForms(int userId) throws SQLException;
	
	/**
	 * This method will return all forms that have the specified status
	 * @param status
	 * @return Form ArrayList
	 * @throws SQLException
	 */
	public ArrayList<Form> getFormsByStatus(int status) throws SQLException;
	
	/**
	 * This method with return a specified form
	 * @param formid
	 * @return Form Object
	 * @throws SQLException
	 */
	public Form getFormBy(int formid) throws SQLException;
	
	/**
	 * This method can be used to change the status of a form
	 * @param formId
	 * @param state
	 * @throws SQLException
	 */
	public void setStatus(int formId, int state) throws SQLException;
	
	/**
	 * This method returns a HashMap that uses the Department ID as the key and the department itself as the stored data
	 * @return HashMap
	 * @throws SQLException
	 */
	public HashMap<Integer, String> getDepartments() throws SQLException;
}