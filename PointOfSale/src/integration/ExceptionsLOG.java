package integration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ExceptionsLOG {

	private static String fileName = "exceptionLog.txt";

	public ExceptionsLOG() {
		File file = new File(fileName);
		if (file.exists()) {
			file.delete();
		}
	}

	/**
	 * Prints the Exception to file.
	 */
	public static void write(Exception ex) {

		try {
			FileWriter fileWriter = new FileWriter(fileName, true);
			fileWriter.write(ex.toString()  + "\n");
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
