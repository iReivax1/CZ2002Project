package SCRAME.Controller;

import SCRAME.Entity.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



// student logic class

public class HumanController {
	
	private ArrayList<Student> studList;
	private ArrayList<Faculty> facList;
	//private ArrayList<Results> resultList;
	Student tempStud = null;

	public void passStudDetails(String studName, String matricNumber, String facultyName, int year) {
		this.addStudent(studName, matricNumber, facultyName, year);
	}
	public void addStudent(String studName, String matricNumber, String facultyName, int year) {
		if(isStudentInList(matricNumber)) {
			// Error message if student alr exist in DB
			System.out.println("Student with same matric number already exists!");
		}
		studList.add(new Student(studName, matricNumber, facultyName, year));
		System.out.println("Student named " + studName + "added!");
	}
	
	public void setStudFacList(ArrayList<Student> studList, ArrayList<Faculty> facList) {
		this.facList = facList;
		this.studList = studList;
	}
	
	/*public void setResultList(ArrayList<Results> resultList) {
		this.resultList = resultList;
	}*/

	//check if student is registered
	public boolean isStudentInList(String matric) {
		for (Student s : studList) {
			if (s.getMatricNumber().equals(matric)) {
				return true;
			}
		}
		return false;
	}

	//return student with matric number
	public Student findStudent(String matric) {
		Student studentFound = null;
		for (Student s : studList) {
			if (s.getMatricNumber().equals(matric)) {
				studentFound = s;
			}
		}
		return studentFound;
	}

	/*@Override
	public void addToFaculty(String facName) {
		// TODO Auto-generated method stub
		for (Faculty f : facList) {
			if(f.equals(facName)) {
				f.setFacultyName(facName);
				break; 
			}
			
		}
		System.out.println("The faculty does not exist!");
	}*/
	
	public boolean addCourse(String matricNumber, String courseFacultyName, String courseCode) {
		Course newCourse = null;
		
		//check if course is already registered by student
		//tempStud is global variable (local in this class) which will be assigned in isCourseRegistered()
		if(isCourseRegistered(matricNumber, courseFacultyName, courseCode))
			return false;
		
		//search for the particular course in the faculty based on faculty name and coursecode given by the boundary
		for(Faculty f : this.facList) {
			//check if student have already registered for the course
			if(f.getFacultyName() == courseFacultyName) {
				for(Course c : f.getCourseList())
				{
					if(c.getCourseCode() == courseCode)
						newCourse = c;
				}
			}
		}
		
		tempStud.addCourse(newCourse);
		
		return true;
	}
	
	public boolean isCourseRegistered(String matricNumber, String courseFacultyName, String courseCode)
	{
		ArrayList<Results> courseRegistered;
		
		//get student object based on student matricNumber
		for(Student s : studList)
		{
			if(s.getMatricNumber() == matricNumber)
			{
				//assign tempStud for addCourse() to use
				tempStud = s;
				break;
			}
		}
				
		//get all course registered by the student
		courseRegistered = tempStud.getResultList();
				
		//check if student have already registered for the course
		for(Results r : courseRegistered) {
			//return function if student already registered for the course
			if(r.getCourse().getCourseCode() == courseCode) {
				return true;
			}
		}
		
		return false;
	}
	
	public ArrayList<Map<String, String>> showStudResults(String matricNumber) {
		//get student object using matricNumber
		Student stud = findStudent(matricNumber);
		
		//resultList is all the results the student have
		ArrayList<Results> resultList = stud.getResultList();
		ArrayList<Map<String, String>> overallResults = new ArrayList<Map<String, String>>();
		Map<String, String> components = new HashMap<String, String>();
		
		String[] TypeList = {"Course Code", "Overall", "Exam", "Coursework"};
		
		for(Results r: resultList) {
			components.put(TypeList[0], r.getCourse().getCourseCode());
			components.put(TypeList[1], Double.toString(r.getTotalScore()));
			components.put(TypeList[2], Double.toString(r.getExamScore()));
			components.put(TypeList[3], Double.toString(r.getCourseworkScore()));
			
			overallResults.add(components);
		}
		return overallResults;
		
		
		/* exception
			if (c.getCourseName().equals(theCourse)){
				if(c.getCourseVacancy() == 0) {
					System.out.println("Sorry, course is already full!");
				}
				else {
					//add course
					CoursesTaken.put(theCourse, -1); // initialize results as -1? or else idk how to do this :/
				}
			}
			else {
				System.out.println("Course code does not exist!");
			}
			*/
	}
	///combine
	public Map<String, Integer> getExamWeightage(String matricNumber) {
		//get student object based on the matricNumber
		Student s = findStudent(matricNumber);
		
		ArrayList<Results> resultList = s.getResultList();
		Map<String, Integer> examWeightageList = new HashMap<String, Integer>();
		for(Results r: resultList) {
			//key is courseCode, value is exam weightage
			examWeightageList.put(r.getCourse().getCourseCode(), r.getExamWeight());
		}
		
		return examWeightageList;
	}
	
	public Map<String, ArrayList<String[]>> showSubWeightage(String matricNumber) {
		//get student object based on the matricNumber
		Student s = findStudent(matricNumber);
		
		ArrayList<Results> resultList = s.getResultList();
		String[] sub;
		ArrayList<String[]> subList = new ArrayList<String[]>();
		Map<String, ArrayList<String[]>> allWeight = new HashMap<String, ArrayList<String[]>>();
		
		//for every result for each course there is 1 arrayList of sub Component. 
		for(Results r: resultList) {
			String courseCode = r.getCourseCode();
			
			//get each component info
			for(SubComponent sc : r.getSubComponent())
			{
				//create new String[] before writing to it
				sub = new String[2];
				sub[0] = sc.getCompName();
				sub[1] = Integer.toString(sc.getWeightage());
				
				subList.add(sub);
			}
			allWeight.put(courseCode, subList);
		}
		
		return allWeight;
	}
		
		
	
	public void passMarks(String matricNumber, String courseCode, double examScore, double courseworkScore) {
		 /*Iterator<Student> student= studList.iterator(); 
	        while (student.hasNext()) 
	            if(((Student) student).getMatricNumber() == martricNumber) {
	            	((Student) student).addMarks(marks, courseCode);
	            }*/
		for (Student s: studList) {
			if(s.getMatricNumber() == matricNumber) {
				for (Results r: s.getResultList()) {
					if(r.getCourse().getCourseCode() == courseCode) {
						s.addExamScore(courseCode, examScore);
						s.addCourseworkScore(courseCode, courseworkScore);
					}
				}
			}
		}
	}
	
	
	


}
