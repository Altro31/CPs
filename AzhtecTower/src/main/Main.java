package main;

import classes.Block;
import classes.Tower;

public class Main {

	public static void main(String[] args) {

		Tower tower = new Tower();

		tower.addBlock(new Block(12));
		tower.addBlock(new Block(25));
		tower.addBlock(new Block(26));
		tower.addBlock(new Block(3));
		tower.addBlock(new Block(18));
		
		for (Block b : tower.getListBlocks()) {
			System.out.println(b.lenght());
		}

	}

}
