package pencil.durability;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PencilTest {
	public static final int POINT_DURABILITY = 10;
	public static final int ERASER_DURABILITY = 50;
	public static final int PENCIL_LENGTH = 5;

	Pencil numberTwoPencil;
	Paper mockPaper;

	@Before
	public void setup(){
		numberTwoPencil = new Pencil(POINT_DURABILITY, ERASER_DURABILITY, PENCIL_LENGTH);
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
}