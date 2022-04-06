package Treatment;

import Constants.Constants;
import Entities.Coordinates;
import Entities.Position;
import Enumerations.Instructions;
import Enumerations.Orientation;
import Exceptions.RoverException;

public class InstructionTreatment {

	private InstructionTreatment() {

	}

	// move forward & return the new coordinates
	// parameters: position & position limit
	public static Coordinates moveForward(Position position, Coordinates coordinatesMax)
			throws RoverException {
		Coordinates nextCoordinates = null;
		int x, y;
		switch (position.getOrientation()) {
		case NORTH:
			x = position.getCoordinates().getX();
			y = position.getCoordinates().getY() + 1;
			break;
		case EAST:
			x = position.getCoordinates().getX() + 1;
			y = position.getCoordinates().getY();
			break;
		case SOUTH:
			x = position.getCoordinates().getX();
			y = position.getCoordinates().getY() - 1;
			break;
		case WEST:
			x = position.getCoordinates().getX() - 1;
			y = position.getCoordinates().getY();
			break;
		default:
			throw new RoverException(Constants.INCORRECT_POSITION);
		}
		nextCoordinates = new Coordinates(x, y);
		// if the new coordinates are behind of Plateau, we keep the last position
		if (nextCoordinates != null && coordinatesMax.isBehindCoordinatesMax(nextCoordinates)) {
			return nextCoordinates;
		} else {
			return position.getCoordinates();
		}
	}

	// turn right & return the new coordinates
	public static Orientation pivoterDroite(Orientation orientation) throws RoverException {
		Orientation nextOrientation = null;
		switch (orientation) {
		case NORTH:
			nextOrientation = Orientation.EAST;
			break;
		case EAST:
			nextOrientation = Orientation.SOUTH;
			break;
		case SOUTH:
			nextOrientation = Orientation.WEST;
			break;
		case WEST:
			nextOrientation = Orientation.NORTH;
			break;
		default:
			throw new RoverException(Constants.INCORRECT_ORIENTATION);
		}
		return nextOrientation;
	}

	// turn left & return the new coordinates
	public static Orientation pivoterGauche(Orientation orientation) throws RoverException {
		Orientation nextOrientation = null;
		switch (orientation) {
		case NORTH:
			nextOrientation = Orientation.WEST;
			break;
		case EAST:
			nextOrientation = Orientation.NORTH;
			break;
		case SOUTH:
			nextOrientation = Orientation.EAST;
			break;
		case WEST:
			nextOrientation = Orientation.SOUTH;
			break;
		default:
			throw new RoverException(Constants.INCORRECT_ORIENTATION);
		}
		return nextOrientation;
	}

	// execute only one instruction ( A, D ou G)
	public static void executeInstruction(Position position, Instructions instruction, Coordinates coordonnesMax)
			throws RoverException {

		switch (instruction) {
		case MOVE:
			position.setCoordinates(InstructionTreatment.moveForward(position, coordonnesMax));
			break;
		case RIGHT:
			position.setOrientation(InstructionTreatment.pivoterDroite(position.getOrientation()));
			break;
		case LEFT:
			position.setOrientation(InstructionTreatment.pivoterGauche(position.getOrientation()));
			break;
		default:
			throw new RoverException(Constants.INSTRUCTION_INCORRECTE);
		}
	}
}
