package pencil.durability;

public class Paper {
	private String text = "";

	public Paper(String initialText){
		this.text = initialText;
	}

	public String getText(){
		return this.text;
	}
}