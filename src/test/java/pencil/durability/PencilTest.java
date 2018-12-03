package pencil.durability;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PencilTest {
	public static final int POINT_DURABILITY = 10;
	public static final int ERASER_DURABILITY = 50;
	public static final int PENCIL_LENGTH = 5;
	@Before
	public void setup(){
		Pencil numberTwoPencil = new Pencil(POINT_DURABILITY, ERASER_DURABILITY, PENCIL_LENGTH);
		Paper mockPaper = mock(Paper.class);
	}

	@Test
	public void testThatAPencilCanBeCreatedWithAPointDurabilityAnEraserDurabilityAndALength(){
		assertEquals(pointDurability, numberTwoPencil.getCurrentPointRemaining());
		assertEquals(eraserDurability, numberTwoPencil.getCurrentEraserRemaining());
		assertEquals(pencilLength, numberTwoPencil.length());
	}

	@Test
	public void testThatUsingAPencilToWriteLowercaseTextOnAPaperDegradesThePointOfThePencil(){
		numberTwoPencil.write("hello", mockPaper);
		verify(mockPaper).addText("hello");
		assertEquals(pointDurability - 5, numberTwoPencil.getCurrentPointRemaining());
	}
}