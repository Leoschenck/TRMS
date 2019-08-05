package com.revature.driver;

import java.sql.SQLException;

import com.revature.daoImpls.UserDaoImpl;

public class Driver {

	public static void main(String[] args) {
		
		UserDaoImpl udi = new UserDaoImpl();
		
		try {
			//System.out.println(udi.getUserById(3));
			System.out.println(udi.getBenCo());
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}
