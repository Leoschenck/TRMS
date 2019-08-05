package com.revature.daoImpls;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.revature.beans.User;
import com.revature.util.ConnFactory;

public class UserDaoImpl {

	ConnFactory cf = ConnFactory.getInstance();
	
	public ArrayList<User> getUsers() throws SQLException {
		ArrayList<User> userList = new ArrayList<User>();
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM \"USER\"");
		User u = null;
		while(rs.next()) {
			u = new User(rs.getString(8),rs.getString(9),rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7));
			userList.add(u);
		}
		return userList;
	}
}
