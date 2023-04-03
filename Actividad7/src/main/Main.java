package main;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

import ej2.Convert;
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
		
		Worker w4 = new Worker("01987654322", "Miguel", "Direccion", 800);
		e.addWorker(w4,0);
		
		Worker w5 = new Worker("01987654323", "Raul", "Direccion", 200);
		e.addWorker(w5);
		
		printWorkers();
		
		System.out.println();

		System.out.println("Los trabajadores del Departamento de Dirección con una salario superior a 500 son:");
		List<Worker> listWorker = e.getWorkersWithSalaryAboveThan500("Direccion");
		for (Worker worker : listWorker) {
			System.out.println(worker.getName()+": "+worker.getSalary());
		}
		
	}
	
	private static void printWorkers() {
		try {
			
			RandomAccessFile file = new RandomAccessFile(Empress.FILE_NAME, "r");
			int nameSize = file.readInt();
			file.skipBytes(nameSize);
			int workerCount = file.readInt();
			System.out.println("Cantidad de Trabajadores: "+workerCount);
			float totalSalary = file.readFloat();
			System.out.println("Salario Total: "+totalSalary+"\n");
			for (int i = 0; i < workerCount; i++) {
				int workerSize = file.readInt();
				byte[] workerBytes = new byte[workerSize];
				file.read(workerBytes);
				
				Worker worker = (Worker) Convert.toObject(workerBytes);
				System.out.println("Trabajador #"+(i+1)+": "+worker.getName()+": "+worker.getSalary()+": Departamento de "+worker.getDepartament());
			}
			
			file.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
