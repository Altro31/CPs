package classes;

public class Block {
	
	private float lenght;
	
	public Block(float lenght) {
		setLenght(lenght);
	}

	public float lenght() {
		return lenght;
	}

	private void setLenght(float lenght) {
		if(lenght<=0)
			throw new IllegalArgumentException("lenght must be greater than 0");
		
		this.lenght = lenght;
	}
}
