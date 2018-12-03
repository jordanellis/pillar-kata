package pencil.durability;

import java.lang.StringBuilder;

public class Paper {
	private String text = "";

	public Paper(String initialText){
		this.text = initialText;
	}

	public String getText(){
		return this.text;
	}

    public void setText(String newText){
        this.text = newText;
    }

    public void addText(String textToAddToPaper){
        String currentText = getText();
        setText(currentText + textToAddToPaper);
    }

    public void removeText(String textToRemoveFromPaper) throws PaperDoesNotContainThatSubstringException {
    	int startIndex = getText().lastIndexOf(textToRemoveFromPaper);
    	if (startIndex < 0){
    		throw new PaperDoesNotContainThatSubstringException(textToRemoveFromPaper);
    	}
    	overwriteEachCharacterForEraseAndInsertion(false, textToRemoveFromPaper, startIndex);
    }

    public void insertTextAtTheGivenIndex(String textToAddToPaper, int startIndex) throws PaperCannotInsertTextAtTheGivenIndexException {
    	if (getText().length() < startIndex) {
    		throw new PaperCannotInsertTextAtTheGivenIndexException(startIndex);
    	}
    	overwriteEachCharacterForEraseAndInsertion(true, textToAddToPaper, startIndex);
    }

    private void overwriteEachCharacterForEraseAndInsertion(boolean isInsertAction, String textToAddToPaper, int startIndex){
		char[] currentTextAsCharArray = getText().toCharArray();
    	for (int i = 0; i < textToAddToPaper.length(); i++){
    		if (isInsertAction && startIndex+i == getText().length()){
    			appendTheRemainingText(currentTextAsCharArray, textToAddToPaper, i);
    			return;
    		}
	    	char characterToAddToCurrentIndex = isInsertAction ? getCurrentCharacterToAdd(textToAddToPaper.charAt(i), currentTextAsCharArray[startIndex + i])
	    													   : ' ';
    		currentTextAsCharArray[startIndex + i] = characterToAddToCurrentIndex;
    	}
		setText(String.valueOf(currentTextAsCharArray));
    }

    private void appendTheRemainingText(char[] currentTextAsCharArray, String textToAddToPaper, int indexOfRemainingText){
		setText(String.valueOf(currentTextAsCharArray));
		String remainingTextToAddToPaper = textToAddToPaper.substring(indexOfRemainingText, textToAddToPaper.length());
		addText(remainingTextToAddToPaper);
    }

    private char getCurrentCharacterToAdd(char initialCharacterToAdd, char characterToOverwrite){
    	if (characterToOverwrite != ' ' && initialCharacterToAdd != ' ')
    		return '@';
    	else if (initialCharacterToAdd == ' ')
    		return characterToOverwrite;
    	else
    		return initialCharacterToAdd;
    }
}
