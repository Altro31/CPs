package ej3;

import java.io.Serializable;

public class Book implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String tittle;
	private String author;
	private int printYear;
	private int amount;
	
	public Book(String id, String tittle, String author, int printYear, int amount) {
		super();
		this.id = id;
		this.tittle = tittle;
		this.author = author;
		this.printYear = printYear;
		this.amount = amount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPrintYear() {
		return printYear;
	}

	public void setPrintYear(int printYear) {
		this.printYear = printYear;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
