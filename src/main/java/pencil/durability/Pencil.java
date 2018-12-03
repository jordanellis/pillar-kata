package pencil.durability;

public class Pencil {
	private final int pointDurabilityRating;
	private int eraserDurability;
	private int pencilLength;

	public Pencil(int initialPointDurability, int initialEraserDurability, int initialPencilLength){
		this.pointDurabilityRating = initialPointDurability;
		this.eraserDurability = initialEraserDurability;
		this.pencilLength = initialPencilLength;
	}

	public int getCurrentPointRemaining(){
		return this.pointDurabilityRating;
	}

	public int getCurrentEraserRemaining(){
		return this.eraserDurability;
	}

	public int length(){
		return this.pencilLength;
	}
}