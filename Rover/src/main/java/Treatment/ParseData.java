package Treatment;

import Enumerations.Instructions;
import Enumerations.Orientation;

public class ParseData {

	private String plateau;
	private String rover;
	private String instructions;

	public ParseData() {
		this.plateau = "";
		this.rover = "";
		this.instructions = "";
	}

	public String getPlateau() {
		return plateau;
	}

	public void setPlateau(String plateau) {
		this.plateau = plateau;
	}

	public String getRover() {
		return rover;
	}

	public void setRover(String rover) {
		this.rover = rover;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	// Return true if rover's informations are incorrect else false
	public boolean executeParse() {
		return parseRover(rover) && parsePlateau(plateau) && parseListInstruction(instructions);
	}

	public static boolean parseRover(String rover) {
		StringBuilder stringBuilder = new StringBuilder("");
		stringBuilder.append(Orientation.NORTH.getCodeOrientation()).append("|")
				.append(Orientation.SOUTH.getCodeOrientation()).append("|")
				.append(Orientation.EAST.getCodeOrientation()).append("|")
				.append(Orientation.WEST.getCodeOrientation());
		return rover.matches("(\\d+) (\\d+) (" + stringBuilder.toString() + ")");
	}

	public static boolean parseListInstruction(String instructions) {
		StringBuilder stringBuilder = new StringBuilder("");
		stringBuilder.append("(").append(Instructions.MOVE.getCodeInstruction()).append("|")
				.append(Instructions.RIGHT.getCodeInstruction()).append("|")
				.append(Instructions.LEFT.getCodeInstruction()).append(")+");

		return instructions.matches(stringBuilder.toString());
	}

	public static boolean parsePlateau(String plateau) {
		return plateau.matches("(\\d+) (\\d+)");
	}
}
