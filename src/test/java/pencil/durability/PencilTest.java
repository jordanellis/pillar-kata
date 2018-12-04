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
    	dullPencil.write("Hello World!", mockPaper);
        verify(mockPaper).addText("Hello       ");
        assertEquals(0, dullPencil.getCurrentPointRemaining());
        dullPencil.sharpen();
        assertEquals(6, dullPencil.getCurrentPointRemaining());
        dullPencil.write("Test", mockPaper);
        verify(mockPaper).addText("Test");
        assertEquals(1, dullPencil.getCurrentPointRemaining());
    }

    @Test
    public void testThatAPencilLosesLengthWhenItIsSharpened(){
        assertEquals(DULL_PENCIL_LENGTH, dullPencil.length());
    	dullPencil.sharpen();
        assertEquals(DULL_PENCIL_LENGTH - 1, dullPencil.length());
    }
}
