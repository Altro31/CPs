package ej3;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import ej2.Convert;

public class Bookshore {

	private File relFile;
	private File relUpdateFile;

	public Bookshore() {
		relFile = new File("Libros.dat");
		relUpdateFile = new File("Actualización.dat");
	}

	public void updateMainDataFile() {

		try {
			RandomAccessFile file = new RandomAccessFile(relFile, "rw");
			RandomAccessFile updateFile = new RandomAccessFile(relUpdateFile, "rw");

			int bookCount = file.readInt();
			int i = 0;
			while (i < bookCount) {

				int bookSize = file.readInt();
				long Pbook = file.getFilePointer();
				byte[] bookBytes = new byte[bookSize];
				file.read(bookBytes);
				Book b = (Book) Convert.toObject(bookBytes);

				char character = updateFile.readChar();
				int idSize = updateFile.readInt();
				updateFile.skipBytes(idSize);
				int amount = updateFile.readInt();

				if (character == 'A') {
					b.setAmount(b.getAmount() + amount);
				} else {
					b.setAmount(b.getAmount() - amount);
				}

				file.seek(Pbook);
				bookBytes = Convert.toBytes(b);
				file.write(bookBytes);

				i++;
			}

			file.close();
			updateFile.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
