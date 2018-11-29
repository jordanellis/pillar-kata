package pencil.durability;

import org.junit.Test;
import static org.junit.Assert.*;

public class PaperTest {
	@Test
	public void testThatAPaperCanBeCreatedWithTextOnIt(){
		Paper testPaper = new Paper("She sells sea shells");
		assertEquals("She sells sea shells", testPaper.getText());
	}

	@Test 
    public void testThatAPaperCanHaveTextAddedToIt() {
        Paper testPaper = new Paper("She sells sea shells");
        testPaper.addText(" down by the sea shore");
        assertEquals("She sells sea shells down by the sea shore", testPaper.getText());
    }

}