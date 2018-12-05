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
	public static final String HELLO = "Hello";
	public static final String LOWERCASE_HELLO = "hello";
	public static final String HELLO_WORLD = "Hello World!";

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
		numberTwoPencil.write(LOWERCASE_HELLO, mockPaper);
		verify(mockPaper).addText(LOWERCASE_HELLO);
		assertEquals(POINT_DURABILITY - 5, numberTwoPencil.getCurrentPointRemaining());
	}

	@Test
	public void testThatUsingAPencilToWriteLowercaseAndUppercaseTextOnAPaperDegradesThePointOfThePencil(){
		numberTwoPencil.write(HELLO, mockPaper);
		verify(mockPaper).addText(HELLO);
		assertEquals(POINT_DURABILITY - 6, numberTwoPencil.getCurrentPointRemaining());
	}

	@Test
	public void testThatUsingAPencilToWriteSpacesOrNewlinesDoesNotDegradeThePointOfThePencil(){
		String helloWorld = "Hello World!\n";
		numberTwoPencil.write(helloWorld, mockPaper);
		verify(mockPaper).addText(helloWorld);
		assertEquals(POINT_DURABILITY - 13, numberTwoPencil.getCurrentPointRemaining());
	}

    @Test
    public void testThatAPencilStopsWritingTheRestOfAWordAfterThePointHasFullyDegraded(){
        dullPencil.write(HELLO_WORLD, mockPaper);
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
	    	dullPencil.write(HELLO_WORLD, mockPaper);
	        verify(mockPaper).addText("Hello       ");
	        assertEquals(0, dullPencil.getCurrentPointRemaining());
	        dullPencil.sharpen();
	        assertEquals(6, dullPencil.getCurrentPointRemaining());
	        String test = "Test";
	        dullPencil.write(test, mockPaper);
	        verify(mockPaper).addText(test);
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
	        dullPencil.write(HELLO, mockPaper);
	        verify(mockPaper).addText(HELLO);
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
    		numberTwoPencil.eraseTextFromPaper(HELLO, mockPaper);
    		verify(mockPaper).removeText(HELLO);
    		assertEquals(ERASER_DURABILITY-5, numberTwoPencil.getCurrentEraserRemaining());
    	} catch (PaperDoesNotContainThatSubstringException exception) {
    		fail(exception.getExceptionMessage());
    	}
    }

    @Test
    public void testThatErasingWhitespaceTextWithAPencilDoesNotDegradeItsEraser(){
    	try {
    		String hello = "H e l l o";
    		numberTwoPencil.eraseTextFromPaper(hello, mockPaper);
    		verify(mockPaper).removeText(hello);
    		assertEquals(ERASER_DURABILITY-5, numberTwoPencil.getCurrentEraserRemaining());
    	} catch (PaperDoesNotContainThatSubstringException exception) {
    		fail(exception.getExceptionMessage());
    	}
    }

    @Test
    public void testThatAnEraserCanDegradeFullyAndThatThePencilWillStopErasing(){
    	try {
    		dullPencil.eraseTextFromPaper(HELLO_WORLD, mockPaper);
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
    		dullPencil.eraseTextFromPaper(HELLO, stubbedPaper);
    		fail("Pencil did not throw a PaperDoesNotContainThatSubstringException.");
    	} catch (PaperDoesNotContainThatSubstringException exception) {
    		assertEquals("This paper does not contain the substring 'Hello'.", exception.getExceptionMessage());
    		assertEquals(DULL_ERASER_DURABILITY, dullPencil.getCurrentEraserRemaining());
    	}
    }

    @Test
    public void testThatAPencilCanEditTextOnAPageAndItWillDegradeThePoint(){
    	try {
    		numberTwoPencil.insertTextAtIndex(LOWERCASE_HELLO, mockPaper, 5);
			verify(mockPaper).insertTextAtTheGivenIndex(LOWERCASE_HELLO, 5);
			assertEquals(POINT_DURABILITY - 5, numberTwoPencil.getCurrentPointRemaining());
		} catch (PaperCannotInsertTextAtTheGivenIndexException exception) {
    		fail(exception.getExceptionMessage());
    	}
    }


    @Test
    public void testThatAPencilStopsInsertingTheRestOfAWordAfterThePointHasFullyDegraded(){
    	try {
			dullPencil.insertTextAtIndex(HELLO_WORLD, mockPaper, 5);
	        verify(mockPaper).insertTextAtTheGivenIndex("Hello       ", 5);
	        assertEquals(0, dullPencil.getCurrentPointRemaining());
		} catch (PaperCannotInsertTextAtTheGivenIndexException exception) {
    		fail(exception.getExceptionMessage());
    	}
    }

    @Test
    public void testThatAttemptingToInsertAtABadIndexThrowsAnExceptionAndDoesNotDegradeThePoint(){
    	try {
    		Paper stubbedPaper = new StubbedPaperThatAlwaysThrowsAnException("");
    		dullPencil.insertTextAtIndex(HELLO, stubbedPaper, 20);
    		fail("Pencil did not throw a PaperCannotInsertTextAtTheGivenIndexException.");
    	} catch (PaperCannotInsertTextAtTheGivenIndexException exception) {
    		assertEquals("This paper cannot perform that edit since index 20 does not exist.", exception.getExceptionMessage());
    		assertEquals(DULL_POINT_DURABILITY, dullPencil.getCurrentPointRemaining());
    	}
    }
}
