package pencil.durability;

public class PencilIsNotLongEnoughToSharpenException extends Exception {
	private String exceptionMessage;

	public PencilIsNotLongEnoughToSharpenException(){
		setExceptionMessage("This pencil is too short and can no longer be sharpened.");
	}

	public String getExceptionMessage(){
		return this.exceptionMessage;
	}

	public void setExceptionMessage(String message){
		this.exceptionMessage = message;
	}
}