package pencil.durability;

class StubbedPaperThatAlwaysThrowsAnException extends Paper {
	public StubbedPaperThatAlwaysThrowsAnException(String initialText){
		super(initialText);
	}

    @Override
    public void removeText(String textToRemoveFromPaper) throws PaperDoesNotContainThatSubstringException {
        throw new PaperDoesNotContainThatSubstringException(textToRemoveFromPaper);
    }
}