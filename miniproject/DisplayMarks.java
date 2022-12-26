package com.miniproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
   
     
public class DisplayMarks {

	

	Quiz quiz = new Quiz();
	Scanner scanner = new Scanner(System.in);
   // Design by Nitin,Suyog,Ravi
	public void getSortData() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject", "root", "1099");
			System.out.println("Records in sorting order");
			ps = con.prepareStatement("select * from student order by Studentname ASC");
			
			//Set set=new TreeSet(ps);
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt(1));
				System.out.println(rs.getString(2));
				System.out.println(rs.getInt(3));
				System.out.println(rs.getString(4));

			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			con.close();
			ps.close();
			rs.close();
		}
	}
       //Design by Roshani,Suyog,Ravi
	public void getStore() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet r = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject", "root", "1099");
			System.out.println("User wants to retrieve ");
			int p = scanner.nextInt();
			if (p == quiz.a) { // if id match with existing id from database then fetch record from databse
				ps = con.prepareStatement("select * from student where StudentId=?");
				ps.setInt(1, quiz.a);
				r = ps.executeQuery();
				while (r.next()) {
					System.out.println(r.getInt(1)); // get student id
					System.out.println(r.getString(2)); // get student name
					System.out.println(r.getString(3)); // get student score
					System.out.println(r.getString(4)); // get student grade
				}

			} else {
				System.out.println("you entered wrong id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
