package ej2;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Student {

	private String name;
	private String ci;
	private List<ResearchGroup> researchGroups;
	private ClassGroup classGroup;
	
	public Student(String name, String ci) {
		setName(name);
		setCi(ci);
		classGroup = null;
		researchGroups = new LinkedList<ResearchGroup>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if(name==null || name.isEmpty())
			throw new IllegalArgumentException("name cannot be null or empty");
		this.name = name;
	}
	public String getCi() {
		return ci;
	}
	public void setCi(String ci) {
		if(ci==null || ci.length()!=11)
			throw new IllegalArgumentException("ci cannot be null and must have 11 characters");
		this.ci = ci;
	}
	public ClassGroup getClassGroup() {
		return classGroup;
	}
	protected void setClassGroup(ClassGroup classGroup) {
		this.classGroup = classGroup;
	}
	
	protected List<ResearchGroup> getResearchGroups() {
		return researchGroups;
	}

	public boolean isInResearchGroup(String scientistTopic) {
		
		boolean res = false;
		Iterator<ResearchGroup> it = researchGroups.iterator();
		while(it.hasNext() && !res) {
			ResearchGroup group = it.next();
			if(group.getTopic().equalsIgnoreCase(scientistTopic)) {
				res = true;
			}
		}
		return res;
	}
}
