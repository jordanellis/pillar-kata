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
    	String currentText = getText();
		char[] currentTextAsCharArray = currentText.toCharArray();
    	int startIndex = currentText.lastIndexOf(textToRemoveFromPaper);
    	if (startIndex < 0){
    		throw new PaperDoesNotContainThatSubstringException(textToRemoveFromPaper);
    	}
    	for (int i = 0; i < textToRemoveFromPaper.length(); i++){
			currentTextAsCharArray[startIndex + i] = ' ';
    	}
		setText(String.valueOf(currentTextAsCharArray));
    }

    public void insertTextAtTheGivenIndex(String textToAddToPaper, int startIndex){
    	String currentText = getText();
    	char[] currentTextAsCharArray = currentText.toCharArray();
    	for (int i = 0; i < textToAddToPaper.length(); i++){
    		if (startIndex+i == currentText.length()){
    			setText(String.valueOf(currentTextAsCharArray));
    			String remainingTextToAddToPaper = textToAddToPaper.substring(i, textToAddToPaper.length());
    			addText(remainingTextToAddToPaper);
    			return;
    		}

    		char characterToAddToCurrentIndex = textToAddToPaper.charAt(i);
    		if (currentTextAsCharArray[startIndex + i] != ' '){
	    		characterToAddToCurrentIndex = '@';
	    	}

    		currentTextAsCharArray[startIndex + i] = characterToAddToCurrentIndex;
    	}
		setText(String.valueOf(currentTextAsCharArray));
    }
}