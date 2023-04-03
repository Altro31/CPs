package aeroline;

import java.time.DayOfWeek;
import java.util.LinkedList;
import java.util.List;

public class WeekDay {
	
	private DayOfWeek day;
	private List<Reservation> reservations;
	private int capacity;
	
	public WeekDay(DayOfWeek day, int capacity) {
		this.day = day;
		this.setCapacity(capacity);
		reservations = new LinkedList<>();
	}
	
	public DayOfWeek getDayOfWeek() {
		return day;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}
	
	public void createReservation(Request r) {
		
		if(capacity==reservations.size())
			throw new RuntimeException("cannot create reservation");
		
		Reservation reservation = new Reservation(r.getNoPassport(), r.getName(), r.getCountry(), reservations.size());
		reservations.add(reservation);
	}
}