package SCRAME.Entity;

import java.util.*;

public class CourseWork {
	
	private ArrayList<SubComponent> subCompList = new ArrayList<SubComponent>();
	protected int subComponentWeightage; 
	protected int courseWorkWeightage; // how much % this coursework is overall i.e. 40
	protected String ComponentName;
	
	public CourseWork(int courseWorkWeightage){
		this.courseWorkWeightage = courseWorkWeightage;
		SubComponent subComp = new SubComponent("default", 100);
		subCompList.add(subComp);
	}
	
	public void createSubComponent(String CompName, int CompWeightage) {
		subCompList.add( new SubComponent(CompName,CompWeightage) );
	}
	
	public ArrayList<SubComponent> getSubCompList(){
		return this.subCompList;
	}

	public void getSubComponent() {
		for(SubComponent s: subCompList) {
			subComponentWeightage = s.getWeightage();
			ComponentName = s.getCompName();
		}
	}

	public int getSubComponentWeightage() {
		return subComponentWeightage;
	}

	public void setSubComponentWeightage(int subComponentWeightage) {
		this.subComponentWeightage = subComponentWeightage;
	}

	public String getComponentName() {
		return ComponentName;
	}

	public void setComponentName(String componentName) {
		ComponentName = componentName;
	}
	
	
}
