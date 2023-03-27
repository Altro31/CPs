package ej2;

import java.util.Iterator;

public class ResearchGroup extends Group {

	private String topic;

	public ResearchGroup(String topic) {
		super();
		this.setTopic(topic);
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public void removeStudent(String name) {
		Iterator<Student> it = studentList.iterator();
		boolean removed = false;
		while (it.hasNext() && !removed) {
			Student st = it.next();
			if (st.getName().equalsIgnoreCase(name)) {
				it.remove();
				removeStudent(st);
			}
		}
	}

	private void removeStudent(Student st) {
		Iterator<ResearchGroup> it = st.getResearchGroups().iterator();
		boolean removed = false;
		while (it.hasNext() && !removed) {
			ResearchGroup rg = it.next();
			if (rg.equals(this)) {
				it.remove();
			}
		}
	}

	public void addStudent(Student st) {
		if (st == null) {
			throw new IllegalArgumentException("student cannot be null");
		}
		if (!studentList.contains(st)) {
			studentList.add(st);
			st.getResearchGroups().add(this);
		}
	}

}
