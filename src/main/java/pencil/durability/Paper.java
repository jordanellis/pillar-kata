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

    public void removeText(String textToRemoveFromPaper){
    	String currentText = getText();
		char[] currentTextAsCharArray = currentText.toCharArray();
    	int startIndex = currentText.lastIndexOf(textToRemoveFromPaper);
    	for (int i = 0; i < textToRemoveFromPaper.length(); i++){
			currentTextAsCharArray[startIndex + i] = ' ';
    	}
		setText(String.valueOf(currentTextAsCharArray));
    }
}