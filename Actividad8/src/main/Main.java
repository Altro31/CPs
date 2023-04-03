package main;

import ej2.ClassGroup;
import ej2.Faculty;
import ej2.ResearchGroup;
import ej2.Student;

public class Main {

	public static void main(String[] args) {

		Faculty fac = new Faculty();

		ClassGroup i_23 = new ClassGroup("I_23");
		fac.getClassGroups().add(i_23);

		ResearchGroup ia = new ResearchGroup("IA");
		fac.getResearchGroups().add(ia);

		Student altro = new Student("Altro", "00000000000");
		fac.getStudentList().add(altro);

		Student diego = new Student("Diego", "11111111111");
		fac.getStudentList().add(diego);

		Student naylet = new Student("Naylet", "22222222222");
		fac.getStudentList().add(naylet);

		i_23.addStudent(diego);
		i_23.addStudent(naylet);
		i_23.addStudent(altro);

		ia.addStudent(naylet);
		ia.addStudent(diego);


		fac.getStudentsInResearchGroup(ia.getTopic());
		i_23.getStudent(diego.getName());
		fac.countStudentsOfClassGroupInResearchGroup(i_23.getIdentifier(), ia.getTopic());
		i_23.removeStudent(altro.getName());
		fac.getStudentsWithoutReasearchGroups();

		System.out.println();
	}

}
