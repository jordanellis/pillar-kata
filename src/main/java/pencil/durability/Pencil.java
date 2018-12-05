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

	public int getPencilDurabilityRating(){
		return this.pointDurabilityRating;
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

	public void setCurrentEraserRemaining(int newEraserRemaining){
		this.eraserDurability = newEraserRemaining;
	}

	public int length(){
		return this.pencilLength;
	}

	public void decreaseLength(){
		this.pencilLength = this.pencilLength - 1;
	}

	public void write(String wordsToWrite, Paper paperToWriteOn){
		wordsToWrite = getTheWordsThatThePencilCanWriteWithItsCurrentPointDurability(wordsToWrite);
		paperToWriteOn.addText(wordsToWrite);
	}

	public void eraseTextFromPaper(String wordsToErase, Paper paperToEraseFrom) throws PaperDoesNotContainThatSubstringException {
		wordsToErase = getTheWordsThatThePencilCanEraseWithItsCurrentEraserDurability(wordsToErase);
		paperToEraseFrom.removeText(wordsToErase);
	}

	public void sharpen() throws PencilIsNotLongEnoughToSharpenException {
		if (length() == 0)
			throw new PencilIsNotLongEnoughToSharpenException();
		decreaseLength();
		setCurrentPointRemaining(getPencilDurabilityRating());
	}

	private String getTheWordsThatThePencilCanWriteWithItsCurrentPointDurability(String initialWordsToWrite){
		String wordsThatCanBeWritten = "";
		for (int i = 0; i < initialWordsToWrite.length(); i++) {
			if (getCurrentPointRemaining() <= 0 || (Character.isUpperCase(initialWordsToWrite.charAt(i)) && getCurrentPointRemaining() <= 1)) {
				wordsThatCanBeWritten = wordsThatCanBeWritten + ' ';
			} else {
				wordsThatCanBeWritten = wordsThatCanBeWritten + initialWordsToWrite.charAt(i);
				if (Character.isUpperCase(initialWordsToWrite.charAt(i))){
					setCurrentPointRemaining(getCurrentPointRemaining() - 2);
				} else if (initialWordsToWrite.charAt(i) != ' ' && initialWordsToWrite.charAt(i) != '\n') {
					setCurrentPointRemaining(getCurrentPointRemaining() - 1);
				}
			}
		}
		return wordsThatCanBeWritten;
	}

	private String getTheWordsThatThePencilCanEraseWithItsCurrentEraserDurability(String initialWordsToErase){
		String wordsThatCanBeErased = "";
		for (int i = initialWordsToErase.length()-1; i >= 0; i--) {
			if (getCurrentEraserRemaining() <= 0) {
				return wordsThatCanBeErased;
			} else {
				wordsThatCanBeErased =  initialWordsToErase.charAt(i) + wordsThatCanBeErased;
				if (initialWordsToErase.charAt(i) != ' ' && initialWordsToErase.charAt(i) != '\n')
					setCurrentEraserRemaining(getCurrentEraserRemaining() - 1);
			}
		}
		return wordsThatCanBeErased;
	}
}
