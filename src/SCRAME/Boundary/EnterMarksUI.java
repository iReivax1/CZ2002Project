package SCRAME.Boundary;

import java.util.Scanner;

import SCRAME.Controller.*;

public class EnterMarksUI {
	static Scanner sc = new Scanner(System.in);
	private HumanController humanCtrl;
	
	public EnterMarksUI() {
	}
	
	public void setHumanController(HumanController humanCtrl) {
		this.humanCtrl = humanCtrl;
	}
	
	//function 7
	public void askForMarks() {
		System.out.println("Enter student's matric number: ");
		String matricNumber = sc.next();
		System.out.println("Enter course code: ");
		String courseCode = sc.nextLine();
		/*System.out.println("Enter total score: ");
		double totalScore = sc.nextDouble();*/
		System.out.println("Enter exam score: ");
		double examScore = sc.nextDouble();
		System.out.println("Enter coursework score: ");
		double courseworkScore = sc.nextDouble();
		
		humanCtrl.passMarks(matricNumber, courseCode, examScore, courseworkScore);
	}
	//funciton 8 not implemented

}
