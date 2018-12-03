package pencil.durability;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PencilTest {
	@Test
	public void testThatAPencilCanBeCreatedWithAPointDurabilityAnEraserDurabilityAndALength(){
		int pointDurability = 10;
		int eraserDurability = 50;
		int pencilLength = 5;
		Pencil numberTwoPencil = new Pencil(pointDurability, eraserDurability, pencilLength);
		assertEquals(pointDurability, numberTwoPencil.getCurrentPointRemaining());
		assertEquals(eraserDurability, numberTwoPencil.getCurrentEraserRemaining());
		assertEquals(pencilLength, numberTwoPencil.length());
	}

	@Test
	public void testThatUsingAPencilToWriteLowercaseTextOnAPaperDegradesThePointOfThePencil(){
		int pointDurability = 10;
		int eraserDurability = 50;
		int pencilLength = 5;
		Pencil numberTwoPencil = new Pencil(pointDurability, eraserDurability, pencilLength);
		Paper mockPaper = mock(Paper.class);
		numberTwoPencil.write("hello", mockPaper);
		verify(mockPaper).addText("hello");
		assertEquals(pointDurability - 5, numberTwoPencil.getCurrentPointRemaining());
	}
}