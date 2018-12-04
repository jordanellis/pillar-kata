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

	public void setCurrentPointRemaining(int newPointRemaining){
		this.currentPointDurability = newPointRemaining;
	}

	public int getCurrentEraserRemaining(){
		return this.eraserDurability;
	}

	public int length(){
		return this.pencilLength;
	}

	public void write(String wordsToWrite, Paper paperToWriteOn){
		wordsToWrite = getTheWordsThatThePencilCanWriteWithItsCurrentPointDurability(wordsToWrite);
		paperToWriteOn.addText(wordsToWrite);
	}

	private String getTheWordsThatThePencilCanWriteWithItsCurrentPointDurability(String initialWordsToWrite){
		String wordsThatCanBeWritten = "";
		for (int i = 0; i < initialWordsToWrite.length(); i++) {
			if (getCurrentPointRemaining() <= 0 || (Character.isUpperCase(initialWordsToWrite.charAt(i)) && getCurrentPointRemaining() <= 1)) {
				return wordsThatCanBeWritten;
			}
			wordsThatCanBeWritten = wordsThatCanBeWritten + initialWordsToWrite.charAt(i);
			if (Character.isUpperCase(initialWordsToWrite.charAt(i))){
				setCurrentPointRemaining(getCurrentPointRemaining() - 2);
			} else if (initialWordsToWrite.charAt(i) != ' ' && initialWordsToWrite.charAt(i) != '\n') {
				setCurrentPointRemaining(getCurrentPointRemaining() - 1);
			}
		}
		return wordsThatCanBeWritten;
	}
}
