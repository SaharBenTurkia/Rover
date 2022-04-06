import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import Constants.Constants;
import Exceptions.RoverException;
import Treatment.FormatLine;
import Treatment.ParseData;

public class Main {

	protected static List<String> resultList;

	public static void main(String... args) throws RoverException, IOException {
		if (args.length == 1) {
			File file = new File(args[0]);
			Main instance = new Main();
			resultList = instance.executeRoverTreatments(file);
		} else {
			throw new IllegalArgumentException();
		}
	}

	// read & validate file & execute rovers
	private List<String> executeRoverTreatments(File file) throws RoverException, IOException {
		if (!file.isFile()) {
			throw new RoverException(Constants.ERROR_INEXISTANT_FILE);
		} else {
			ParseData parser = new ParseData();
			Scanner scanner = new Scanner(file);
			try {
				FormatLine.TreatFirstLine(parser, scanner);
				return FormatLine.TreatNextLines(parser, scanner);
			} finally {
				if (scanner != null) {
					scanner.close();
				}
			}
		}
	}
}
