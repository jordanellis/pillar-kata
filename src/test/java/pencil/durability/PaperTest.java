package pencil.durability;

import org.junit.Test;
import static org.junit.Assert.*;

public class PaperTest {
	@Test
	public void testThatAPaperCanBeCreatedWithTextOnIt(){
		Paper testPaper = new Paper("She sells sea shells");
		assertEquals("She sells sea shells", testPaper.getText());
	}
}