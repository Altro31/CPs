package aeroline;

public class Reservation {
	
	private String noPassport;
	private String name;
	private String country;
	private int seat;
	
	
	public Reservation(String noPassport, String name, String country, int seat) {
		this.noPassport = noPassport;
		this.name = name;
		this.country = country;
		this.seat = seat;
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


	public int getSeat() {
		return seat;
	}


	public void setSeat(int seatCount) {
		this.seat = seatCount;
	}
	
	
}
