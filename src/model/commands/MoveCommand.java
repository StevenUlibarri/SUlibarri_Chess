package model.commands;

public class MoveCommand {
	
	private Location beginLocation;
	private Location endLocation;
	private boolean takes;
	
	public MoveCommand(Location bl, Location el, Boolean t) {
		beginLocation = bl;
		endLocation = bl;
		takes = t;
	}

	public Location getBeginLocation() {
		return beginLocation;
	}

	public void setBeginLocation(Location beginLocation) {
		this.beginLocation = beginLocation;
	}

	public Location getEndLocation() {
		return endLocation;
	}

	public void setEndLocation(Location endLocation) {
		this.endLocation = endLocation;
	}

	public boolean isTakes() {
		return takes;
	}

	public void setTakes(boolean takes) {
		this.takes = takes;
	}
	
	public String toString() {
		String verb = (takes) ? " takes on " : " moves to ";
		return "Piece at " + beginLocation + verb + endLocation;
		
	}
	
}
