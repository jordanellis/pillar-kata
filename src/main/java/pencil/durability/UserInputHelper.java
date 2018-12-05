package pencil.durability;

import java.io.PrintStream;
import java.io.InputStream;
import java.util.Scanner;

public class UserInputHelper {
	private final Scanner inputStream;
    private final PrintStream outputStream;

    public Scanner getInputStream(){
    	return this.inputStream;
    }

    public PrintStream getOutputStream(){
    	return this.outputStream;
    }

    public UserInputHelper(InputStream newInputStream, PrintStream newOutputStream) {
        this.inputStream = new Scanner(newInputStream);
        this.outputStream = newOutputStream;
    }
}