package pencil.durability;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class PaperTest {
	private static final String SHE_SELLS_SEA_SHELLS = "She sells sea shells";
	private static final String DOWN_BY_THE_SEA_SHORE = " down by the sea shore";
	Paper testPaper;

	@Before
	public void setup(){
		testPaper = new Paper(SHE_SELLS_SEA_SHELLS);
	}

	@Test
	public void testThatAPaperCanBeCreatedWithTextOnIt(){
		assertEquals(SHE_SELLS_SEA_SHELLS, testPaper.getText());
	}

	@Test 
    public void testThatAPaperCanHaveTextAddedToIt(){
        testPaper.addText(DOWN_BY_THE_SEA_SHORE);
        assertEquals(SHE_SELLS_SEA_SHELLS + DOWN_BY_THE_SEA_SHORE, testPaper.getText());
    }

    @Test
    public void testThatAPaperCanHaveTextRemovedFromIt(){
    	testPaper.removeText("sea");
    	assertEquals("She sells     shells", testPaper.getText());
    }
}