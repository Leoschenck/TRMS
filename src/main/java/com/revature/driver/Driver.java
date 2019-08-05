package com.revature.driver;

import java.sql.Date;
import java.sql.SQLException;

import com.revature.daoImpls.FormDaoImpl;
import com.revature.daoImpls.UserDaoImpl;

public class Driver {

	public static void main(String[] args) {
		
		UserDaoImpl udi = new UserDaoImpl();
		FormDaoImpl fdi = new FormDaoImpl();
		
		
		try {
			//System.out.println(udi.getUserById(3));
			System.out.println(udi.getBenCo());
			//fdi.createForm(Date.valueOf("2020-05-21"), "FSU", "Mediocre course to learn coding!", 150000.00, "LetterGrade", "Coding-Course", "", 0, "", udi.getBenCo().getId(), 3);
			System.out.println(fdi.getFormsByUserId(udi.getBenCo().getId()));
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}
