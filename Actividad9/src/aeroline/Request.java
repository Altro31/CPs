package aeroline;

import java.time.DayOfWeek;

public class Request {
	
	private String noPassport;
	private String name;
	private String country;
	private DayOfWeek day;
	
	public Request(String noPassport, String name, String country, DayOfWeek day) {
		super();
		this.noPassport = noPassport;
		this.name = name;
		this.country = country;
		this.day = day;
	}

	public String getNoPassport() {
		return noPassport;
	}

	public void setNoPassport(String noPassport) {
		this.noPassport = noPassport;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public DayOfWeek getDay() {
		return day;
	}

	public void setDay(DayOfWeek day) {
		this.day = day;
	}
	
	
}
