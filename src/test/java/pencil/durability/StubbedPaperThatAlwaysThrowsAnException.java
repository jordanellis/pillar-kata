package pencil.durability;

class StubbedPaperThatAlwaysThrowsAnException extends Paper {
	public StubbedPaperThatAlwaysThrowsAnException(String initialText){
		super(initialText);
	}

    @Override
    public void removeText(String textToRemoveFromPaper) throws PaperDoesNotContainThatSubstringException {
        throw new PaperDoesNotContainThatSubstringException(textToRemoveFromPaper);
    }

	@Override
    public void insertTextAtTheGivenIndex(String textToAddToPaper, int startIndex) throws PaperCannotInsertTextAtTheGivenIndexException {
    	throw new PaperCannotInsertTextAtTheGivenIndexException(startIndex);
    }
}