package com.revature.Dao;

import java.sql.SQLException;
import java.util.ArrayList;
import com.revature.beans.User;

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
public interface UserDao {

	/**
	 * This method will return an ArrayList of users.
	 * @return User ArrayList
	 * @throws SQLException
	 */
	public ArrayList<User> getUsers() throws SQLException;
	
	/**
	 * This method checks for a username and password and returns a User object.
	 * @param username
	 * @param password
	 * @return User Object
	 * @throws SQLException
	 */
	public User loginUser(String username, String password) throws SQLException;
	
	/**
	 * Method used to change the total reimbursement amount.
	 * @param newAmount
	 * @throws SQLException
	 */
	public void changeReimbursementAmount(double newAmount, int userId) throws SQLException;
	
	/**
	 * Resets the reimbursement amount to 1000 for all users.
	 * @throws SQLException
	 */
	public void resetReimbursementAllUsers() throws SQLException;
	
	/**
	 * This method is used to check if a user is the Benefits Coordinator.
	 * @param userid
	 * @return String Object
	 * @throws SQLException
	 */
	public String isBenco(int userid) throws SQLException;
	
	/**
	 * This method will return the user that the form belongs to.
	 * @param formid
	 * @return String Object
	 * @throws SQLException
	 */
	public int getUserIdByFormId(int formid) throws SQLException;
	
	/**
	 * This method will return the user with the specified id.
	 * @param id
	 * @return User Object
	 * @throws SQLException
	 */
	public User getUserById(int id) throws SQLException;
	
	/**
	 * This method will return the user who is the Benefits Coordinator.
	 * @return User Object
	 * @throws SQLException
	 */
	public User getBenCo() throws SQLException;
}