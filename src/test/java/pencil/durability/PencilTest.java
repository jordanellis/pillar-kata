package pencil.durability;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PencilTest {
	public static final int POINT_DURABILITY = 20;
	public static final int ERASER_DURABILITY = 50;
	public static final int PENCIL_LENGTH = 5;
	public static final int DULL_POINT_DURABILITY = 6;
	public static final int DULL_ERASER_DURABILITY = 5;
	public static final int DULL_PENCIL_LENGTH = 1;

	Pencil numberTwoPencil;
	Pencil dullPencil;
	Paper mockPaper;

	@Before
	public void setup(){
		numberTwoPencil = new Pencil(POINT_DURABILITY, ERASER_DURABILITY, PENCIL_LENGTH);
		dullPencil = new Pencil(DULL_POINT_DURABILITY, DULL_ERASER_DURABILITY, DULL_PENCIL_LENGTH);
		mockPaper = mock(Paper.class);
	}

	@Test
	public void testThatAPencilCanBeCreatedWithAPointDurabilityAnEraserDurabilityAndALength(){
		assertEquals(POINT_DURABILITY, numberTwoPencil.getCurrentPointRemaining());
		assertEquals(ERASER_DURABILITY, numberTwoPencil.getCurrentEraserRemaining());
		assertEquals(PENCIL_LENGTH, numberTwoPencil.length());
	}

	@Test
	public void testThatUsingAPencilToWriteLowercaseTextOnAPaperDegradesThePointOfThePencil(){
		numberTwoPencil.write("hello", mockPaper);
		verify(mockPaper).addText("hello");
		assertEquals(POINT_DURABILITY - 5, numberTwoPencil.getCurrentPointRemaining());
	}

	@Test
	public void testThatUsingAPencilToWriteLowercaseAndUppercaseTextOnAPaperDegradesThePointOfThePencil(){
		numberTwoPencil.write("Hello", mockPaper);
		verify(mockPaper).addText("Hello");
		assertEquals(POINT_DURABILITY - 6, numberTwoPencil.getCurrentPointRemaining());
	}

	@Test
	public void testThatUsingAPencilToWriteSpacesOrNewlinesDoesNotDegradeThePointOfThePencil(){
		numberTwoPencil.write("Hello World!\n", mockPaper);
		verify(mockPaper).addText("Hello World!\n");
		assertEquals(POINT_DURABILITY - 13, numberTwoPencil.getCurrentPointRemaining());
	}

    @Test
    public void testThatAPencilStopsWritingTheRestOfAWordAfterThePointHasFullyDegraded(){
        dullPencil.write("Hello World!", mockPaper);
        verify(mockPaper).addText("Hello       ");
        assertEquals(0, dullPencil.getCurrentPointRemaining());
    }

    @Test
    public void testThatAPencilSkipsACapitalLetterIfItIsNotSharpEnoughToWriteIt(){
        dullPencil.write("hello World!", mockPaper);
        verify(mockPaper).addText("hello  o    ");
        assertEquals(0, dullPencil.getCurrentPointRemaining());
    }

    @Test
    public void testThatAPencilCanBeSharpenedAndCanContinueToWriteAfterwards(){
		try {
	    	dullPencil.write("Hello World!", mockPaper);
	        verify(mockPaper).addText("Hello       ");
	        assertEquals(0, dullPencil.getCurrentPointRemaining());
	        dullPencil.sharpen();
	        assertEquals(6, dullPencil.getCurrentPointRemaining());
	        dullPencil.write("Test", mockPaper);
	        verify(mockPaper).addText("Test");
	        assertEquals(1, dullPencil.getCurrentPointRemaining());
        } catch (PencilIsNotLongEnoughToSharpenException exception) {
    		fail(exception.getExceptionMessage());
    	}
    }

    @Test
    public void testThatAPencilLosesLengthWhenItIsSharpened(){
    	try {
	        assertEquals(DULL_PENCIL_LENGTH, dullPencil.length());
	    	dullPencil.sharpen();
	        assertEquals(DULL_PENCIL_LENGTH - 1, dullPencil.length());
        } catch (PencilIsNotLongEnoughToSharpenException exception) {
    		fail(exception.getExceptionMessage());
    	}
    }

    @Test
    public void testThatAPencilThrowsAnExceptionAfterTryingToSharpenItWithNoLength(){
    	try {
	        assertEquals(DULL_PENCIL_LENGTH, dullPencil.length());
	    	dullPencil.sharpen();
	        assertEquals(DULL_PENCIL_LENGTH - 1, dullPencil.length());
	        dullPencil.write("Hello", mockPaper);
	        verify(mockPaper).addText("Hello");
	        assertEquals(0, dullPencil.getCurrentPointRemaining());
	    	dullPencil.sharpen();
    		fail("Pencil did not throw a PencilIsNotLongEnoughToSharpenException.");
    	} catch (PencilIsNotLongEnoughToSharpenException exception) {
    		assertEquals("This pencil is too short and can no longer be sharpened.", exception.getExceptionMessage());
	        assertEquals(0, dullPencil.length());
	        assertEquals(0, dullPencil.getCurrentPointRemaining());
    	}
    }

    @Test
    public void testThatErasingNonwhitespaceTextWithAPencilDegradesItsEraser(){
    	try {
    		numberTwoPencil.eraseTextFromPaper("Hello", mockPaper);
    		verify(mockPaper).removeText("Hello");
    		assertEquals(ERASER_DURABILITY-5, numberTwoPencil.getCurrentEraserRemaining());
    	} catch (PaperDoesNotContainThatSubstringException exception) {
    		fail(exception.getExceptionMessage());
    	}
    }

    @Test
    public void testThatErasingWhitespaceTextWithAPencilDoesNotDegradeItsEraser(){
    	try {
    		numberTwoPencil.eraseTextFromPaper("H e l l o", mockPaper);
    		verify(mockPaper).removeText("H e l l o");
    		assertEquals(ERASER_DURABILITY-5, numberTwoPencil.getCurrentEraserRemaining());
    	} catch (PaperDoesNotContainThatSubstringException exception) {
    		fail(exception.getExceptionMessage());
    	}
    }

    @Test
    public void testThatAnEraserCanDegradeFullyAndThatThePencilWillStopErasing(){
    	try {
    		dullPencil.eraseTextFromPaper("Hello World!", mockPaper);
    		verify(mockPaper).removeText("orld!");
    		assertEquals(0, dullPencil.getCurrentEraserRemaining());
    	} catch (PaperDoesNotContainThatSubstringException exception) {
    		fail(exception.getExceptionMessage());
    	}
    }

    @Test
    public void testThatAttemptingToEraseAStringThatAPaperDoesNotContainThrowsAnExceptionAndDoesNotDegradeTheEraser(){
    	try {
    		Paper stubbedPaper = new StubbedPaperThatAlwaysThrowsAnException("");
    		dullPencil.eraseTextFromPaper("Hello", stubbedPaper);
    		fail("Pencil did not throw a PaperDoesNotContainThatSubstringException.");
    	} catch (PaperDoesNotContainThatSubstringException exception) {
    		assertEquals("This paper does not contain the substring 'Hello'.", exception.getExceptionMessage());
    		assertEquals(DULL_ERASER_DURABILITY, dullPencil.getCurrentEraserRemaining());
    	}
    }
}
