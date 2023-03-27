package ej2;

import java.util.Iterator;
import java.util.ListIterator;

public class ClassGroup extends Group {

	private String identifier;

	public ClassGroup(String identidier) {
		super();
		this.setIdentifier(identidier);
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	// Binary Search
	public Student getStudent(String name) {

		Student res = null;

		int left = 0;
		int right = studentList.size() - 1;

		while (left <= right && res == null) {
			int mid = left + (right - left) / 2;
			Student st = studentList.get(mid);
			if (st.getName().equalsIgnoreCase(name)) {
				res = st;
			} else if (st.getName().compareTo(name) < 0) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		return res;
	}

	public int countStudentsInResearchGroup(String scientistTopic) {

		int res = 0;
		Iterator<Student> it = studentList.iterator();
		while (it.hasNext()) {
			Student st = it.next();
			if (st.isInResearchGroup(scientistTopic)) {
				res++;
			}
		}
		return res;
	}

	public void removeStudent(String name) {
		Iterator<Student> it = studentList.iterator();
		boolean removed = false;
		while (it.hasNext() && !removed) {
			Student st = it.next();
			if (st.getName().equalsIgnoreCase(name)) {
				it.remove();
				st.setClassGroup(null);
			}
		}

	}

	public void addStudent(Student st) {

		if (st == null)
			throw new IllegalArgumentException("st cannot be null");

		if (!studentList.contains(st)) {
			ListIterator<Student> it = studentList.listIterator();
			boolean added = false;
			while (it.hasNext() && !added) {
				Student comp = it.next();
				if (st.getName().compareTo(comp.getName()) <= 0) {
					it.previous();
					it.add(st);
					added = true;
				}
			}

			if (!added) {
				studentList.add(st);
			}
		}
		if (studentList.contains(st)) {
			st.setClassGroup(this);
		}
	}
}
