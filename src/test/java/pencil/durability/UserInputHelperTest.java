package pencil.durability;

import org.junit.Test;
import static org.junit.Assert.*;

public class UserInputHelperTest {
	@Test
	public void testThatAnInputHelperCanBeCreatedWithAnInputStreamAndAPrintStream(){
		UserInputHelper userInputHelper = new UserInputHelper(System.in, System.out);
		assertNotNull(userInputHelper.getInputStream());
		assertNotNull(userInputHelper.getOutputStream());
	}
}