package pencil.durability;

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
}