package com.revature.daoImpls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.beans.Form;
import com.revature.beans.User;
import com.revature.util.ConnFactory;

public class UserDaoImpl {

	ConnFactory cf = ConnFactory.getInstance();

	public ArrayList<User> getUsers() throws SQLException {
		ArrayList<User> userList = new ArrayList<User>();
		Connection conn = cf.getConnection();
		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM \"USER\"");
		ResultSet rs = stmt.executeQuery();
		User u = null;
		while (rs.next()) {
			u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getDouble(6), rs.getInt(7), rs.getString(8), rs.getString(9));
			userList.add(u);
		}
		return userList;
	}

	public int loginUser(String username, String password) throws SQLException {
		Connection conn = cf.getConnection();
		PreparedStatement stmt = conn.prepareStatement(
				"SELECT * FROM \"USER\" WHERE UserName = '" + username + "' AND Password = '" + password + "'");
		ResultSet rs = stmt.executeQuery();
		int u = -1;
		if (rs.next()) {
			u = (rs.getInt(1));
		}
		System.out.println("in loginuser with " + username + " and " + password);
		return u;
	}

	public String isBenco(int userid) {
		Connection conn = cf.getConnection();
		try {
			PreparedStatement stmt = conn
					.prepareStatement("SELECT bencoid FROM benco WHERE bencoid = " + userid);
			ResultSet rs = stmt.executeQuery();
			return rs.next()?"1":"0";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "0";
	}

	public int getUserByFormId(int formid) throws SQLException {
		Connection conn = cf.getConnection();
		PreparedStatement ps = conn.prepareStatement("SELECT employeeid FROM form WHERE formid = " + formid);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		}
		return -1;
	}

	public User getUserById(int id) throws SQLException {
		ArrayList<User> userList = new ArrayList<User>();
		Connection conn = cf.getConnection();
		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM \"USER\" WHERE userid = " + id);
		ResultSet rs = stmt.executeQuery();
		User u = null;
		if (rs.next()) {

			return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getDouble(6), rs.getInt(7), rs.getString(8), rs.getString(9));
		}
		return null;
	}

	public User getBenCo() throws SQLException {
		ArrayList<User> userList = new ArrayList<User>();
		Connection conn = cf.getConnection();
		PreparedStatement stmt = conn
				.prepareStatement("SELECT * FROM \"USER\" WHERE userid = (SELECT bencoid FROM BENCO)");
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {

			return new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getDouble(6), rs.getInt(7), rs.getString(8), rs.getString(9));
		}
		return null;
	}

}