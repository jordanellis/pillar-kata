package pencil.durability;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class PaperTest {
	private static final String SHE_SELLS_SEA_SHELLS = "She sells sea shells";
	private static final String DOWN_BY_THE_SEA_SHORE = " down by the sea shore";
	private static final String CHUCK = "chuck";
	Paper sheSellsSeaShellsPaper;
	Paper woodChuckPaper;
	Paper aBlankADayPaper;
	Paper helloWorldPaper;

	@Before
	public void setup(){
		sheSellsSeaShellsPaper = new Paper(SHE_SELLS_SEA_SHELLS);
		woodChuckPaper = new Paper("How much wood would a woodchuck chuck if a woodchuck could chuck wood?");
		aBlankADayPaper = new Paper("An       a day keeps the doctor away");
		helloWorldPaper = new Paper("Hello world!");
	}

	@Test
	public void testThatAPaperCanBeCreatedWithTextOnIt(){
		assertEquals(SHE_SELLS_SEA_SHELLS, sheSellsSeaShellsPaper.getText());
	}

	@Test 
	public void testThatAPaperCanHaveTextAddedToIt(){
		sheSellsSeaShellsPaper.addText(DOWN_BY_THE_SEA_SHORE);
		assertEquals(SHE_SELLS_SEA_SHELLS + DOWN_BY_THE_SEA_SHORE, sheSellsSeaShellsPaper.getText());
	}

	@Test
	public void testThatAPaperCanHaveTextRemovedFromIt(){
		try {
			sheSellsSeaShellsPaper.removeText("sea");
			assertEquals("She sells     shells", sheSellsSeaShellsPaper.getText());
		} catch (PaperDoesNotContainThatSubstringException exception) {
			fail(exception.getExceptionMessage());
		}
	}

	@Test
	public void testThatAPaperCanHaveTextRemovedFromItMultipleTimes(){
		try {
			woodChuckPaper.removeText(CHUCK);
			assertEquals("How much wood would a woodchuck chuck if a woodchuck could       wood?", woodChuckPaper.getText());
			woodChuckPaper.removeText(CHUCK);
			assertEquals("How much wood would a woodchuck chuck if a wood      could       wood?", woodChuckPaper.getText());
		} catch (PaperDoesNotContainThatSubstringException exception) {
			fail(exception.getExceptionMessage());
		}
	}

	@Test
	public void testThatAPaperThrowsAnExceptionIfAUserTriesToRemoveASubstringThatIsNotThere(){
		try {
			sheSellsSeaShellsPaper.removeText(CHUCK);
			fail("Paper did not throw PaperDoesNotContainThatSubstringException.");
		} catch (PaperDoesNotContainThatSubstringException exception) {
			assertEquals("This paper does not contain the substring 'chuck'.", exception.getExceptionMessage());
		}
	}

	@Test
	public void testThatAPaperCanHaveTextEditedIntoWhitespace(){
		Paper aBlankADayPaper = new Paper("An       a day keeps the doctor away");
		try {
			aBlankADayPaper.insertTextAtTheGivenIndex("onion", 3);
			assertEquals("An onion a day keeps the doctor away", aBlankADayPaper.getText());
		} catch (PaperCannotInsertTextAtTheGivenIndexException exception) {
			fail(exception.getExceptionMessage());
		}
	}

	@Test
	public void testThatAPaperCanHaveTextEditedIntoWhitespaceAndOverOtherCharacters(){
		try {
			aBlankADayPaper.insertTextAtTheGivenIndex("artichoke", 3);
			assertEquals("An artich@k@ay keeps the doctor away", aBlankADayPaper.getText());
		} catch (PaperCannotInsertTextAtTheGivenIndexException exception) {
			fail(exception.getExceptionMessage());
		}
	}

	@Test
	public void testThatAPaperCanHaveTextEditedAndHaveItExtendBeyondTheCurrentLength(){
		try {
			helloWorldPaper.insertTextAtTheGivenIndex("everyone!", 6);
			assertEquals("Hello @@@@@@ne!", helloWorldPaper.getText());
		} catch (PaperCannotInsertTextAtTheGivenIndexException exception) {
			fail(exception.getExceptionMessage());
		}
	}

	@Test
	public void testThatAPaperThrowsAnExceptionIfAUserTriesToEditTextAtAnIndexGreaterThanTheCurrentLength(){
		try {
			helloWorldPaper.insertTextAtTheGivenIndex("everyone!", 20);
			fail("Paper did not throw PaperDoesNotContainThatSubstringException.");
		} catch (PaperCannotInsertTextAtTheGivenIndexException exception) {
			assertEquals("This paper cannot perform that edit since index 20 does not exist.", exception.getExceptionMessage());
		}
	}

	@Test
	public void testThatAPaperCanHaveTextEditedIntoWhitespaceAndOverOtherCharactersAndAlsoAddSpacesOvertopOfText(){
		try {
			aBlankADayPaper.insertTextAtTheGivenIndex("artichoke   ", 3);
			assertEquals("An artich@k@ay keeps the doctor away", aBlankADayPaper.getText());
		} catch (PaperCannotInsertTextAtTheGivenIndexException exception) {
			fail(exception.getExceptionMessage());
		}
	}
}
