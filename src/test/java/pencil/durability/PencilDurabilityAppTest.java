package pencil.durability;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.*;

public class PencilDurabilityAppTest {
	private final ByteArrayOutputStream applicationOutputContent = new ByteArrayOutputStream();
	private final PrintStream originalSystemOut = System.out;
	private PencilDurabilityApp pencilDurabilityApp;

	@Before
	public void setup() {
		pencilDurabilityApp = new PencilDurabilityApp();
	    System.setOut(new PrintStream(applicationOutputContent));
	}


	@Test
	public void testThatTheMainAppDisplaysASetOfOptionsToTheUser(){
		pencilDurabilityApp.printOutOptionsToTheUser();
    	assertEquals("1 - Create New Pencil\n2 - View Current Pencil State\n3 - Create New Paper\n4 - View Current Paper\n5 - Write Text\n6 - Insert Text\n7 - Erase Text\n8 - Sharpen Pencil\n", applicationOutputContent.toString());
	}

	@After
	public void resetOutputStream() {
	    System.setOut(originalSystemOut);
	}
}