package main;

import ej2.Empress;
import ej2.Worker;

public class Main {

	public static void main(String[] args) {

		Empress e = new Empress("TRADEX");
		e.createNewFile();

		Worker w1 = new Worker("12345678910", "Maria", "Informatica", 300);
		e.addWorker(w1);
		
		Worker w2 = new Worker("01987654321", "Carlos", "Direccion", 1000);
		e.addWorker(w2);
		
		Worker w3= new Worker("646446464654", "Jose", "Informatica", 600);
		e.addWorker(w3);
		
		Worker w4 = new Worker("01987654321", "Miguel", "Direccion", 800);
		e.addWorker(w4,1);
		
		Worker w5 = new Worker("01987654321", "Raul", "Direccion", 200);
		e.addWorker(w5);

		e.getWorkersWithSalaryAboveThan500("Direccion");
		
	}

}
