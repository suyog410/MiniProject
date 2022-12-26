package com.miniproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Quiz {
    static int a;
	static String show;
	static int correct = 0;
	static int wrong = 0;
 

		
	
      //Design by  Suyog,Ravi
	public void getRandomData() throws SQLException {
		Scanner scanner = new Scanner(System.in);    //to take user input
              Connection con=null;
              PreparedStatement ps=null;
             
		try {
			Class.forName("com.mysql.jdbc.Driver"); // Loading Driver Class
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject", "root",
					"1099");

		 ps = con.prepareStatement("select * from project order by rand()");// get random
			// questions
			// from database
		   ResultSet rs = ps.executeQuery();

			System.out.println("Enter the Student Name");
			String name = scanner.next(); // taking name from user
			PreparedStatement ps1 = con.prepareStatement("select * from student where Studentname=?");
			ps1.setString(1, name); // set student name into table
			ResultSet rs1 = ps1.executeQuery();
			String StdName = null;

			System.out.println("Enter the ID");
			 a = scanner.nextInt();// taking user input
			PreparedStatement ps2 = con.prepareStatement("select*from student where StudentId=?");
			ps2.setInt(1, a);  //set student id into table
			ResultSet rs2 = ps2.executeQuery();

			if (rs1.next() == false) { // checking name is present in database or not
				System.out.println("Register for Attempt Test");
				System.out.println("enter the name");
				String Username = scanner.next();
				System.out.println("enter the Id");
				int b = scanner.nextInt();
				PreparedStatement ps3 = con.prepareStatement("insert into student(StudentId,Studentname)values(?,?);");
				//ps3.setInt(1, b); // in column 1 set studentid
				ps3.setString(2, Username); // in column 2 set student name
				ps3.setInt(1, b); // in column 1 set studentid

				int c = ps3.executeUpdate();
				System.out.println("Details recorded Successfully" );
			}
			if (rs2.next() == false) { // check studentid exist in database or not
				System.out.println("Enter your Id");
			} else {
				while (rs.next()) {
					while (rs1.next()) {
						StdName = rs1.getString(2);
						System.out.println("StdName" + StdName);
					}
					while (rs2.next()) {
					     a = rs2.getInt(1);
					}
					System.out.println(rs.getString(1)); // get column 1 details
					System.out.println(rs.getString(2)); // get column 2 details
					System.out.println(rs.getString(3)); // get column 3 details
					System.out.println(rs.getString(4)); // get column 4 details
					System.out.println(rs.getString(5)); // get column 5 details
					System.out.println(rs.getString(6)); // get column 6 details
					System.out.println("Enter your Answer");
					String Userinput = scanner.next(); // userinput for correct answer
					String d = rs.getString(7); // store correct answer in d variable
					if (d.equals(Userinput)) { // compares user answer with database ani.e (column 7)
						correct++; // for every correct option count is increase
						System.out.println("correct choice>>" + rs.getString(7));
					} else {
						wrong++;
						System.out.println(" wrong choice>>correct choice " + rs.getString(7));
					}
				}
				// conditions for grade   //Done by Nitin
				System.out.println("Score out of 10>>" + correct);
				if (correct >= 7 && correct <= 10) {
					show = "Class A";

				} else if (correct >= 6 && correct <= 8) {
					show = "Class B";

				} else if (correct == 5) {
					show = "Class C";
				} else if (correct>= 0 && correct<=5) {
					show = "Class D";
					System.out.println("Fail");
				}
				System.out.println("Congratulations Your Test is Submitted");
				System.out.println("Test Analysis");
				System.out.println(" Correct Answers " + correct);
				System.out.println(" Wrong Answers " + wrong);

				System.out.println(" Your Score is " + correct);

				PreparedStatement ps4 = con.prepareStatement("update student set Score=?,Gradeclass=? where StudentId=?;");
				ps4.setInt(1, correct);
				ps4.setString(2, show);                      
				ps4.setInt(3, a);
			    ps4.executeUpdate();
			System.out.println();
			}
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			con.close();
			ps.close();
			
		}
	}
}
