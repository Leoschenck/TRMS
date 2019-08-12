package com.revature.Dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.revature.beans.Notification;

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
public interface NotificationDao {

	/**
	 * This method can be used to create a notification
	 * @param formID
	 * @param content
	 * @throws SQLException
	 */
	void createNotification(int formID, String content) throws SQLException;
	
	/**
	 * This method can be used to get all visible notifications
	 * @param employeeID
	 * @return List of notifications
	 * @throws SQLException
	 */
	public List<Notification> getNotifications(int employeeID) throws SQLException;
	
	/**
	 * This method can be used to display both visible and previously hidden notifications
	 * @param employeeID
	 * @return List of notifications
	 * @throws SQLException
	 */
	public List<Notification> showAllNotifications(int employeeID) throws SQLException;
	
	/**
	 * This method can be used to hide a specific notification
	 * @param notificationID
	 * @throws SQLException
	 */
	public void HideNotification(int notificationID) throws SQLException;


}
