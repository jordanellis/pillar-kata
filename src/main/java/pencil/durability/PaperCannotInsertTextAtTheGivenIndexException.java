package pencil.durability;

public class PaperCannotInsertTextAtTheGivenIndexException extends Exception {
	private String exceptionMessage;

	public PaperCannotInsertTextAtTheGivenIndexException(int index){
		setExceptionMessage("This paper cannot perform that edit since index " + String.valueOf(index) + " does not exist.");
	}

	public String getExceptionMessage(){
		return this.exceptionMessage;
	}

	public void setExceptionMessage(String message){
		this.exceptionMessage = message;
	}
}