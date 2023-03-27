package ej2;

import java.util.LinkedList;
import java.util.List;

public abstract class Group {

	protected List<Student> studentList;

	protected Group() {
		studentList = new LinkedList<Student>();
	}

	
	/**
	 * Devuelve la lista de estudiantes de este grupo. Esta lista no es la original
	 * por lo que los cambios realizados sobre ella no serán reflejados en la
	 * original
	 */
	protected List<Student> getStudentList() {
		return studentList;
	}
	
	protected abstract void removeStudent(String name);
	protected abstract void addStudent(Student st);
}
