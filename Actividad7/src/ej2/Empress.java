package ej2;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;
import java.util.List;

public class Empress {

	private static final String FILE_NAME = "EmpressData.DAT";

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

	// Incisi a
	public void createNewFile() {
		try {
			RandomAccessFile file = new RandomAccessFile(this.fileDIR, "rw");

			byte[] nameBytes = Convert.toBytes(name);
			file.writeInt(nameBytes.length);
			file.write(nameBytes);
			file.writeInt(0);
			file.writeFloat(0);

			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Inciso b
	public void addWorker(Worker w) {

		try {
			RandomAccessFile file = new RandomAccessFile(this.fileDIR, "rw");

			int nameSize = file.readInt();
			file.skipBytes(nameSize);

			long PworkerCount = file.getFilePointer();

			int workerCount = file.readInt();
			float totalSalary = file.readFloat();

			int i = 0;
			boolean stop = false;
			while (i < workerCount && !stop) {

				int workerSize = file.readInt();
				byte[] workerBytes = new byte[workerSize];
				file.read(workerBytes);
				Worker worker = (Worker) Convert.toObject(workerBytes);
				if (worker.getName().equalsIgnoreCase(w.getName())) {
					stop = true;
				}
				i++;
			}

			if (!stop) {
				byte[] wBytes = Convert.toBytes(w);
				file.writeInt(wBytes.length);
				file.write(wBytes);

				file.seek(PworkerCount);
				file.writeInt(workerCount + 1);
				file.writeFloat(totalSalary + w.getSalary());
			}

			file.close();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Inciso d
	public void addWorker(Worker w, int position) {

		try {
			RandomAccessFile file = new RandomAccessFile(this.fileDIR, "rw");

			int nameSize = file.readInt();
			file.skipBytes(nameSize);

			long PworkerCount = file.getFilePointer();

			int workerCount = file.readInt();
			float totalSalary = file.readFloat();

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
				if (worker.getName().equalsIgnoreCase(w.getName())) {
					stop = true;
				}
				i++;
			}

			if (!stop) {
				int endFile = (int) file.getFilePointer();

				file.seek(Pposition);

				int rest = (int) (endFile - Pposition);

				byte[] restFile = new byte[rest];
				file.read(restFile);

				file.seek(Pposition);

				byte[] wBytes = Convert.toBytes(w);
				file.writeInt(wBytes.length);
				file.write(wBytes);

				file.write(restFile);

				file.seek(PworkerCount);
				file.writeInt(workerCount + 1);
				file.writeFloat(totalSalary + w.getSalary());
			}

			file.close();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// Incisio c
	public List<Worker> getWorkersWithSalaryAboveThan500(String departament) {

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

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return workerList;

	}
}
