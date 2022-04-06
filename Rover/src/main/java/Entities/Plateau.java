package Entities;

public class Plateau {

private Coordinates positionLimit;
    

	public Plateau(Coordinates positionLimit) {
		super();
		this.positionLimit = positionLimit;
	}


	public Coordinates getPositionLimit() {
		return positionLimit;
	}


	public void setPositionLimit(Coordinates positionLimit) {
		this.positionLimit = positionLimit;
	}
}
