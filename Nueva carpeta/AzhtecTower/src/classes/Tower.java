package classes;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * La clase Tower representa una torre azteca que está formada por bloques
 * rectangulares representados por la clase Block.
 * 
 * Tower contiene una lista enlazada de Bloques donde el primer bloque
 * representa la címa de la torre y el último bloque representa la base. Éstos
 * bloques se encuentran ordenados de forma ascendente desde la base hasta la
 * cima.
 * 
 * Perspectiva: La Torre se mira desde arriba hacia abajo
 * 
 * IMPORTANTE: No se sigue el criterio de una pila para este caso, por lo que
 * las inserciones se pueden realizar en cualquier posición.
 * 
 * @author The Altro
 *
 */

public class Tower {

	/**
	 * Representa los bloques de la torre
	 */
	private LinkedList<Block> listBlocks;

	public Tower() {
		listBlocks = new LinkedList<Block>();
	}

	public LinkedList<Block> getListBlocks() {
		return listBlocks;
	}

	/**
	 * Añade un bloque a la lista de bloques. El elemento añadido se posicionará de
	 * tal forma que se respete el orden ascendente de la lista
	 * 
	 * @param toAdd Elemento Bloque que será añadido
	 */
	public void addBlock(Block toAdd) {

		// Para el bucle si se vuelve true
		boolean added = false;

		ListIterator<Block> it = listBlocks.listIterator();
		while (!added && it.hasNext()) {
			Block block = it.next();
			if (block.lenght() >= toAdd.lenght()) {
				// Inserta el elemento atras de block /////
				it.previous();
				it.add(toAdd);
				//////////////////////////////////////////
				added = true;
			}
		}

		/**
		 * Caso en que inicialmente la lista esté vacía o no se haya encontrado en el
		 * bucle un bloque que sea mayor que el que se valla a añadir (el bloque a
		 * añadir sería el mayor).
		 * 
		 * Para ambos caso, el bloque se añade al final de la lista.
		 */

		if (!added) {
			listBlocks.add(toAdd);
		}
	}
}
