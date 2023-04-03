package aeroline;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;

public class Aeroline {

	private ArrayList<Flight> flights;
	private Queue<Request> notSatisfactied;
	private Queue<Request> requestes;

	public Aeroline() {
		flights = new ArrayList<>();
		notSatisfactied = new ArrayDeque<Request>();
		requestes = new ArrayDeque<Request>();
	}

	public void assignRequestes() {

		Queue<Request> rs = new ArrayDeque<>(requestes);
		rs.addAll(notSatisfactied);
		while (!rs.isEmpty()) {
			Request r = notSatisfactied.poll();
			assignRequest(r);
		}
	}

	private void assignRequest(Request r) {

		ArrayList<Flight> list = getFlights(r);
		Iterator<Flight> fit = list.iterator();
		boolean inserted = false;
		while (!inserted && fit.hasNext()) {
			Flight f = fit.next();
			Iterator<WeekDay> it = f.getFlightDays().iterator();
			try {
				while (it.hasNext()) {
					WeekDay day = it.next();
					if (day.getDayOfWeek().compareTo(r.getDay()) == 0) {
						day.createReservation(r);
						inserted = true;
					}

				}
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}
		if (!inserted) {
			notSatisfactied.offer(r);
		}
	}

	public ArrayList<Flight> getFlights(Request r) {
		ArrayList<Flight> list = new ArrayList<>();

		for (Flight f : flights) {
			if (f.getDestination().equalsIgnoreCase(r.getCountry())) {
				list.add(f);
			}
		}

		return list;
	}
}
