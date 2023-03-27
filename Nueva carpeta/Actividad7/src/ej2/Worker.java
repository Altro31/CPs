package ej2;

import java.io.Serializable;

public class Worker implements Serializable {

	private static final long serialVersionUID = 7109681923863996575L;
	private String id;
	private String name;
	private String departament;
	private float salary;

	public Worker(String id, String name, String departament, float salary) {
		this.id = id;
		this.name = name;
		this.departament = departament;
		this.setSalary(salary);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartament() {
		return departament;
	}

	public void setDepartament(String departament) {
		this.departament = departament;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}
}
