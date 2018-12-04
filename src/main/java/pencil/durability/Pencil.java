package pencil.durability;

public class Pencil {
	private final int pointDurabilityRating;
	private int currentPointDurability;
	private int eraserDurability;
	private int pencilLength;

	public Pencil(int initialPointDurability, int initialEraserDurability, int initialPencilLength){
		this.pointDurabilityRating = initialPointDurability;
		this.currentPointDurability = initialPointDurability;
		this.eraserDurability = initialEraserDurability;
		this.pencilLength = initialPencilLength;
	}

	public int getCurrentPointRemaining(){
		return this.currentPointDurability;
	}

	public void setCurrentPointRemaing(int newPointRemaining){
		this.currentPointDurability = newPointRemaining;
	}

	public int getCurrentEraserRemaining(){
		return this.eraserDurability;
	}

	public int length(){
		return this.pencilLength;
	}

	public void write(String wordsToWrite, Paper paperToWriteOn){
		int totalCostOfWords = numberOfCharactersThatAreNeitherSpaceNorNewlines(wordsToWrite) + numberOfUppercaseCharacters(wordsToWrite);
		setCurrentPointRemaing(getCurrentPointRemaining() - totalCostOfWords);
		paperToWriteOn.addText(wordsToWrite);
	}

	private int numberOfUppercaseCharacters(String text){
		int numberOfUppercaseCharacters = 0;
		for(int i = 0; i < text.length(); i++) {
			if (Character.isUpperCase(text.charAt(i)))
				numberOfUppercaseCharacters++;
		}
		return numberOfUppercaseCharacters;
	}

	private int numberOfCharactersThatAreNeitherSpaceNorNewlines(String text){
		int numberOfCharacters = 0;
		for(int i = 0; i < text.length(); i++) {
			if (text.charAt(i) != ' ' && text.charAt(i) != '\n')
				numberOfCharacters++;
		}
		return numberOfCharacters;
	}
}
