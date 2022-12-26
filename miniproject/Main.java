package com.miniproject;

import java.util.Scanner;
  //Design by Suyog,Ravi,Nitin, Roshani
public class Main {

	public static void main(String[] args) {

		Quiz quiz = new Quiz(); // creating objects of classes
		DisplayMarks displaymarks = new DisplayMarks();

		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter the number of Students to Attempt test");
		int d = scanner.nextInt();
		try {
			for (int i = 1; i <= d; i++) {         //for multiple students taking test
				System.out.println(" number of Students " + d);

				quiz.getRandomData();
				displaymarks.getStore();
				
			}
			displaymarks.getSortData();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
