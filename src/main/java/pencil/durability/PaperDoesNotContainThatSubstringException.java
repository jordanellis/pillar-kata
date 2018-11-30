package pencil.durability;

public class PaperDoesNotContainThatSubstringException extends Exception {
	private String exceptionMessage;

	public PaperDoesNotContainThatSubstringException(String substring){
		setExceptionMessage("This paper does not contain the substring '" + substring + "'.");
	}

	public String getExceptionMessage(){
		return this.exceptionMessage;
	}

	public void setExceptionMessage(String message){
		this.exceptionMessage = message;
	}
}