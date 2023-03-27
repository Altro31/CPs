package ej2;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Faculty {

	private List<ClassGroup> classGroups;
	private List<ResearchGroup> researchGroups;
	private List<Student> studentList;

	public Faculty() {
		this.classGroups = new LinkedList<ClassGroup>();
		this.researchGroups = new LinkedList<ResearchGroup>();
		this.studentList = new LinkedList<Student>();
	}

	public List<ClassGroup> getClassGroups() {
		return classGroups;
	}

	public List<ResearchGroup> getResearchGroups() {
		return researchGroups;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public ResearchGroup getResearchGroup(String topic) {

		ResearchGroup res = null;
		Iterator<ResearchGroup> it = researchGroups.iterator();
		while (it.hasNext() && res == null) {
			ResearchGroup group = it.next();
			if (group.getTopic().equalsIgnoreCase(topic)) {
				res = group;
			}
		}

		return res;
	}

	public List<Student> getStudentsInResearchGroup(String topic) {
		return getResearchGroup(topic).getStudentList();
	}

	public ClassGroup getClassGroup(String identifier) {

		ClassGroup res = null;
		Iterator<ClassGroup> it = classGroups.iterator();
		while (it.hasNext() && res == null) {
			ClassGroup group = it.next();
			if (group.getIdentifier().equalsIgnoreCase(identifier)) {
				res = group;
			}
		}

		return res;
	}

	public int countStudentsOfClassGroupInResearchGroup(String classIdentifier, String scientistTopic) {
		ClassGroup group = getClassGroup(classIdentifier);
		return group.countStudentsInResearchGroup(scientistTopic);
	}

	public List<Student> getStudentsWithoutReasearchGroups() {
		List<Student> res = new LinkedList<>();
		Iterator<Student> it = studentList.iterator();
		while (it.hasNext()) {
			Student st = it.next();
			if (st.getResearchGroups().isEmpty()) {
				res.add(st);
			}
		}
		return res;
	}

}
