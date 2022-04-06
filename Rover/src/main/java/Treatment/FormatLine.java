package Treatment;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Constants.Constants;
import Entities.Coordinates;
import Entities.Plateau;
import Entities.Position;
import Entities.Rover;
import Enumerations.Instructions;
import Enumerations.Orientation;
import Exceptions.RoverException;

public class FormatLine {

	private static final String SPACE_STRING = " ";

	private FormatLine() {

	}

	// recup rover's position
	public static Position formatRoverLine(String roverLine) {
		String[] elts = roverLine.split(SPACE_STRING);
		Coordinates pRoverCoordinates = new Coordinates(Integer.valueOf(elts[0]), Integer.valueOf(elts[1]));
		Orientation roverOrientation = getOrientation(elts[2].charAt(0));
		return new Position(pRoverCoordinates, roverOrientation);
	}

	// recup plateau limit position
	public static Plateau formatPlateauLine(String line) {
		String[] elts = line.split(SPACE_STRING);
		return new Plateau(new Coordinates(Integer.valueOf(elts[0]), Integer.valueOf(elts[1])));
	}

	// recup instruction list enum
	public static List<Instructions> formatInstructionLine(String ligneInstruction) {
		List<Instructions> listInstruction = new ArrayList<Instructions>();
		for (char instruction : ligneInstruction.toCharArray()) {
			listInstruction.add(getInstruction(instruction));
		}
		return listInstruction;
	}

	// recup orientation enum corresponding to character
	public static Orientation getOrientation(char cOrientation) {
		for (Orientation orientation : Orientation.values()) {
			if (orientation.getCodeOrientation() == cOrientation) {
				return orientation;
			}
		}
		return null;
	}

	// recup instruction enum
	public static Instructions getInstruction(char cInstruction) {
		for (Instructions instruction : Instructions.values()) {
			if (instruction.getCodeInstruction() == cInstruction) {
				return instruction;
			}
		}
		return null;
	}

	// treat first line in file
	public static void TreatFirstLine(ParseData parser, Scanner scanner) throws RoverException {
		if (scanner.hasNext()) {
			parser.setPlateau(scanner.nextLine());
		}
		if (!scanner.hasNext()) {
			throw new RoverException(Constants.ERROR_INCORRECT_DATA);
		}
	}

	// return rover's position
	public static List<String> TreatNextLines(ParseData parser, Scanner scanner) throws RoverException {
		List<String> positionList = new ArrayList<String>();
		while (scanner.hasNext()) {
			// read intial line
			parser.setRover(scanner.nextLine());

			if (scanner.hasNext()) {
				parser.setInstructions(scanner.nextLine());
				positionList.add(parseAndExecuteTreatment(parser));
			} else {
				throw new RoverException(Constants.ERROR_INCORRECT_DATA);
			}
		}
		return positionList;
	}

	// parse and execute rover's treatment
	private static String parseAndExecuteTreatment(ParseData parser) throws RoverException {
		if (!parser.executeParse()) {
			throw new RoverException(Constants.ERROR_INCORRECT_DATA);
		} else {
			Rover treatment = new Rover();
			treatment.setPlateau(FormatLine.formatPlateauLine(parser.getPlateau()));
			treatment.setPosition(FormatLine.formatRoverLine(parser.getRover()));
			treatment.setInstructionList(FormatLine.formatInstructionLine(parser.getInstructions()));
			treatment.executeInstructions();
			System.out.println(treatment);
			return treatment.toString();
		}
	}
}
