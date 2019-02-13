package SCRAME.Boundary;

import SCRAME.Controller.*;
import java.util.Scanner;

public class AddStudentUI implements IAddBoundary {
	static Scanner sc = new Scanner(System.in);
	private HumanController humanCtrl;

	public AddStudentUI() {
	}
	
	// take in HumanController obj
	public void setHumanController(HumanController humanCtrl) {
		this.humanCtrl = humanCtrl;
	}
	
	// display and ask for user input
	public void askStudDetails() {
		String studName, matricNumber, facultyName;
		int year;
		
		System.out.println("Enter name for student: ");
		studName = sc.nextLine();
		System.out.println("Enter Matriculation Number: ");
		matricNumber = sc.next().toUpperCase();
		System.out.println("Enter Faculty of student: ");
		facultyName = sc.next().toUpperCase();
		System.out.println("Enter year student is in: ");
		year = sc.nextInt();
		
		humanCtrl.passStudDetails(studName, matricNumber, facultyName, year); //pass back to StudentController
	}
	
}
