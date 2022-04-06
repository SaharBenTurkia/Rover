package Entities;

import java.util.ArrayList;
import java.util.List;

import Enumerations.Instructions;
import Exceptions.RoverException;
import Treatment.InstructionTreatment;

public class Rover {

	private Plateau plateau;
	private Position position;
	private List<Instructions> instructionList;

	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public void setInstructionList(List<Instructions> pInstructionList) {
		this.instructionList = pInstructionList;
		if (pInstructionList == null) {
			instructionList = new ArrayList<Instructions>();
		}
	}

	// execute the group of instructions per rover
	public void executeInstructions() throws RoverException {
		for (Instructions instruction : instructionList) {
			InstructionTreatment.executeInstruction(position, instruction, this.plateau.getPositionLimit());
		}
	}

	@Override
	public String toString() {
		return position.getCoordinates().getX() + " " + position.getCoordinates().getY() + " "
				+ position.getOrientation().getCodeOrientation();
	}
}
