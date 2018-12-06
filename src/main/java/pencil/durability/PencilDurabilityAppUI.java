package pencil.durability;

public class PencilDurabilityAppUI {
	private static final String MENU = "1 - Create New Pencil\n2 - View Current Pencil State\n3 - Create New Paper\n4 - View Current Paper\n5 - Write Text\n6 - Insert Text\n7 - Erase Text\n8 - Sharpen Pencil\n9 - Exit\n\nPlease enter a number to run the corresponding action:";
	private Paper paper;
	private Pencil pencil;

	public void init(){
		this.paper = new Paper("");
		this.pencil = new Pencil(5, 5, 5);
	}

	private Pencil getPencil(){
		return this.pencil;
	}

	private void setPencil(Pencil newPencil){
		this.pencil = newPencil;
	}

	private Paper getPaper(){
		return this.paper;
	}

	private void setPaper(Paper newPaper){
		this.paper = newPaper;
	}

	public void run(){
		init();
		UserInputHelper inputHelper = new UserInputHelper(System.in, System.out);
		boolean continueRunning = true;
		while (continueRunning){
			String response = inputHelper.askUserForInput(MENU);
			switch (response) {
				case "1":
					handleCreateNewPencil(inputHelper);
					break;
				case "2":
					handleViewCurrentPencil();
					break;
				case "3":
					handleCreateNewPaper();
					break;
				case "4":
					handleViewCurrentPaper();
					break;
				case "5":
					handleWriteText(inputHelper);
					break;
				case "6":
					handleInsertText(inputHelper);
					break;
				case "7":
					handleEraseText(inputHelper);
					break;
				case "8":
					handleSharpenPencil();
					break;
				case "9":
					continueRunning = false;
					break;
				default:
					break;
			}
		}
	}

	private void handleCreateNewPaper(){
		setPaper(new Paper(""));
		System.out.println("New Paper Created!");
	}

	private void handleViewCurrentPaper(){
		Paper currentPaper = getPaper();
		System.out.println("\n---Current Paper---");
		System.out.println(currentPaper.getText());
	}

	private void handleCreateNewPencil(UserInputHelper inputHelper){
		String pencilDurability = inputHelper.askUserForInput("Enter the pencil durability:");
		String eraserDurability = inputHelper.askUserForInput("Enter the eraser durability:");
		String length = inputHelper.askUserForInput("Enter the pencil length:");
		Pencil newPencil = new Pencil(Integer.valueOf(pencilDurability), Integer.valueOf(eraserDurability), Integer.valueOf(length));
		setPencil(newPencil);
	}

	private void handleViewCurrentPencil(){
		Pencil currentPencil = getPencil();
		System.out.println("\n-----Current Pencil-----");
		System.out.println("Point Durability  = " + currentPencil.getCurrentPointRemaining());
		System.out.println("Eraser Durability = " + currentPencil.getCurrentEraserRemaining());
		System.out.println("Pencil Length     = " + currentPencil.length());
		System.out.println("------------------------\n\n");
	}

	private void handleWriteText(UserInputHelper inputHelper){
		String text = inputHelper.askUserForInput("Enter the text you'd like to write:");
		getPencil().write(text, getPaper());
	}

	private void handleInsertText(UserInputHelper inputHelper){
		String text = inputHelper.askUserForInput("Enter the text you'd like to insert:");
		String index = inputHelper.askUserForInput("Enter the index you'd like to insert it at:");
		try {
			getPencil().insertTextAtIndex(text, getPaper(), Integer.valueOf(index));
		} catch (PaperCannotInsertTextAtTheGivenIndexException exception) {
			System.out.println(exception.getExceptionMessage());
		}
	}

	private void handleEraseText(UserInputHelper inputHelper){
		String text = inputHelper.askUserForInput("Enter the text you'd like to erase:");
		try {
			getPencil().eraseTextFromPaper(text, getPaper());
		} catch (PaperDoesNotContainThatSubstringException exception) {
			System.out.println(exception.getExceptionMessage());
		}
	}

	private void handleSharpenPencil(){
		try {
			getPencil().sharpen();
		} catch (PencilIsNotLongEnoughToSharpenException exception) {
			System.out.println(exception.getExceptionMessage());
		}
	}
}
