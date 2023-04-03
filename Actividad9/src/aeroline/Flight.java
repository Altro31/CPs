package aeroline;

import java.util.ArrayList;

public class Flight {
	
	private String number;
	private int capacity;
	private String destination;
	private ArrayList<WeekDay> flightDays;
	
	public Flight(String number, int capacity, String destination) {
		
		this.number = number;
		this.capacity = capacity;
		this.destination = destination;
		flightDays = new ArrayList<>();
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public ArrayList<WeekDay> getFlightDays() {
		return flightDays;
	}
}
