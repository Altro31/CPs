package ej2;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;
import java.util.List;

public class Empress {

	public static final String FILE_NAME = "EmpressData.DAT";
	private String name;
	private File fileDIR;

	public Empress(String name) {
		this.name = name;
		fileDIR = new File(FILE_NAME);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Inicializa un fichero con un encabezado y sin nigún trabajador.
	 * El encabezado tiene la forma: 
	 * 1. (int)Tamaño del Nombre de la Empresa
	 * 2. (String)Nombre de la Empresa
	 * 3. (int)Cantidad de Trabajadores
	 * 4. (float) Salario Total de los Trabajadores
	 * 
	 * @IMPORTANTE Antes de trabajar con el fichero se debe llamar a este metodo
	 * para que lo inicialize correctamente
	 */
	public void createNewFile() {
		try {
			//Abre el fichero
			RandomAccessFile file = new RandomAccessFile(this.fileDIR, "rw");

			//Convierte de name en secuencia de Bytes
			byte[] nameBytes = Convert.toBytes(name);
			
			//Escribe el tamaño de name en Bytes
			file.writeInt(nameBytes.length);
			
			//Escribe name en Bytes
			file.write(nameBytes);
			
			//Escribe la cantidad de Trabajadores inicial
			file.writeInt(0);
			
			//Escribe el Salario Total
			file.writeFloat(0);

			//Cierra el fichero
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Añade un trabajador al fichero. El trabajador se añade al final de
	 * los demás que ya estén. Si no hay ningún trabajador, queda como el único
	 * de la lista
	 * @param w Trabajador que se valla a insertar
	 * @throws IllegalArgumentException Si w es null
	 */
	public void addWorker(Worker w) {

		if(w==null)
			throw new IllegalArgumentException("w cannot bu null");
		
		try {
			//Abre el fichero
			RandomAccessFile file = new RandomAccessFile(this.fileDIR, "rw");

			//Lee el nombre de la Empresa
			int nameSize = file.readInt();
			//Salta los Bytes del Nombre porque no son necesarios
			file.skipBytes(nameSize);

			//Guarda la posicion del Puntero antes de leer la cantidad de
			//trabajadores (workerCount)
			long PworkerCount = file.getFilePointer();

			//Lee la cantida de Trabajadores
			int workerCount = file.readInt();
			
			//Lee es salario total
			float totalSalary = file.readFloat();

			/**
			 * Ciclo que recorre los trabajadores del fichero y verifica que
			 * el trabajador que se vaya a añadir no esté en el fichero. De ser 
			 * el caso contrario, stop se vuelve true y para el ciclo
			 */
			int i = 0;
			boolean stop = false;
			while (i < workerCount && !stop) {

				//Lee tamaño de trabajador
				int workerSize = file.readInt();
				byte[] workerBytes = new byte[workerSize];
				
				//Lee trabajador
				file.read(workerBytes);
				
				//Convierte trabajador
				Worker worker = (Worker) Convert.toObject(workerBytes);
				if (worker.getId().equals(w.getId())) {
					stop = true;
				}
				
				//incremento
				i++;
			}

			/**
			 * Tener en cuenta que al finalizar el ciclo sin interrupciones
			 * (o sea que stop se mantenga false) el puntero del fichero
			 * se encontrará al final
			 */
			
			if (!stop) {
				
				//Convierte el trabajador a secuencia de Bytes
				byte[] wBytes = Convert.toBytes(w);
				
				//Escribe el tamaño del trabajador en bytes
				file.writeInt(wBytes.length);
				
				//Escribe el trabajador el bytes
				file.write(wBytes);

				//Regresa a la posición en el fichero antes de de la
				//cantida de trabajadores (workerCount)
				file.seek(PworkerCount);
				
				//Aumenta en uno la cantida de trabajadores del fichero
				file.writeInt(workerCount + 1);
				
				//Aumenta el salario total de la cantidad de trabajadores
				//del fichero
				file.writeFloat(totalSalary + w.getSalary());
			}

			//Cierra el fichero
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Añade un trabajador al fichero en una determinada posicion.
	 * El trabajador se añade al final de los demás que ya estén si pos sobrepasa
	 * la cantidad de trabajadores. Si no hay ningún trabajador, queda como 
	 * el único de la lista.
	 * Las posiciones de los trabajadores comienzan en 0.
	 * 
	 * @param w Trabajador que se valla a insertar
	 * @param position Posicion en la que se va a insertar el trabajador
	 * 
	 * @throws IllegalArgumentException si w es null ó position menor que 0
	 */
	public void addWorker(Worker w, int position) {

		if(w==null)
			throw new IllegalArgumentException("w cannot be null");
		
		if(position<0)
			throw new IllegalArgumentException("position cannot be negative");
		
		try {
			//Abre el fichero
			RandomAccessFile file = new RandomAccessFile(this.fileDIR, "rw");

			//Lee tamaño del nombre de la empresa
			int nameSize = file.readInt();
			
			//Salta los bytes que corresponden la nombre de la empresa
			file.skipBytes(nameSize);

			//Guarda la posición del puntero detrás de la cantidad de trabajadores
			long PworkerCount = file.getFilePointer();

			//Lee la cantidad de trabajadores
			int workerCount = file.readInt();
			
			/**
			 * Si position es mayor que la cantidad de trabajadores, entonces
			 * position se iguala a la cantidad de trabajadores
			 */
			if(position>workerCount)
				position = workerCount;
			
			//Lee salario total
			float totalSalary = file.readFloat();

			/**
			 * Ciclo que recorre los trabajadores del fichero y verifica que
			 * el trabajador que se vaya a añadir no esté en el fichero. De ser 
			 * el caso contrario, stop se vuelve true y para el ciclo. Además cuando
			 * se encuentre la posición en la que se debe añadir el trabajador, se guardará
			 * una referencia a ella para posteriormente situar el puentero del fichero
			 * ahí y añadir el nuevo trabajador
			 */
			int i = 0;
			boolean stop = false;
			long Pposition = -1;
			while (i < workerCount && !stop) {

				if (i == position) {
					Pposition = file.getFilePointer();
				}

				int workerSize = file.readInt();
				byte[] workerBytes = new byte[workerSize];
				file.read(workerBytes);
				Worker worker = (Worker) Convert.toObject(workerBytes);
				if (worker.getId().equals(w.getId())) {
					stop = true;
				}
				i++;
			}
			
			/**
			 * Tener en cuenta que al finalizar el ciclo sin interrupciones
			 * (o sea que stop se mantenga false) el puntero del fichero
			 * se encontrará al final
			 */

			if (!stop) {
				
				//En éste punto, el puntero del fichero está situado al final
				//bytes correspondiente a todos los trabajadores. Se guarda esa posición
				long endFile = file.getFilePointer();
				
				//Si Pposition se mantiene menor que 0 (o sea -1) significa
				//Que no hay trabajadores o que se debe añadir el trabajador
				//Al final
				if(Pposition<0)
					Pposition = endFile;

				//Ubica el puntero del fichero en la posicion en la que se va a 
				//añadir el trabajador
				file.seek(Pposition);

				//Calcula los bytes que hay entre la posición en la que se debe
				//añadir el trabajador y el final
				int rest = (int) (endFile - Pposition);

				//Si existe el resto, entonces esos bytes se guardan y se vuelve
				//a posicionar el puntero del fichero en la posición en la que se debe
				//añadir el trabajador (Esto debido a que con ésta operación se cambia
				//la posición del puntero)
				byte[] restFile = null;
				if(rest>0) {
					restFile = new byte[rest];
					file.read(restFile);
					file.seek(Pposition);
				}

				//Se escribe en el fichero tanto el tamaño en Bytes como el trabajador en Bytes
				byte[] wBytes = Convert.toBytes(w);
				file.writeInt(wBytes.length);
				file.write(wBytes);

				//Si existen bytes restantes, adicionarlos luego del trabajador insertado
				if(restFile!=null)
					file.write(restFile);

				//Actualiza los valores de cantidad de trabajadores y 
				//Salario total
				file.seek(PworkerCount);
				file.writeInt(workerCount + 1);
				file.writeFloat(totalSalary + w.getSalary());
			}

			//Cierra el fichero
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Obtiene los trabajadores con un salario mayor que 500 
	 * y que pertenezcan a un departamento dado
	 * @param departament Departamento en que deben estar los empleados
	 * @return Una lista de trabajadores
	 * @throws IllegalArgumentException si <code> departament </code> es null o está vacío
	 */
	public List<Worker> getWorkersWithSalaryAboveThan500(String departament) {

		if(departament==null || departament.isEmpty())
			throw new IllegalArgumentException("departament cannot be null or empty");
		
		List<Worker> workerList = new LinkedList<Worker>();

		try {

			RandomAccessFile file = new RandomAccessFile(this.fileDIR, "rw");

			int nameSize = file.readInt();
			file.skipBytes(nameSize);
			int workerCount = file.readInt();
			file.readFloat();

			int i = 0;
			while (i < workerCount) {

				int workerSize = file.readInt();
				byte[] workerBytes = new byte[workerSize];
				file.read(workerBytes);
				Worker worker = (Worker) Convert.toObject(workerBytes);
				if (worker.getDepartament().equalsIgnoreCase(departament) && worker.getSalary() > 500) {
					workerList.add(worker);
				}
				i++;
			}
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return workerList;

	}
}
