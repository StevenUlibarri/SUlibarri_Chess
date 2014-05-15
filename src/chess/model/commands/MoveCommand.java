package chess.model.commands;

import chess.model.board.Location;

public class MoveCommand implements Executable{
	
	private Location beginLocation;
	private Location endLocation;
	private boolean takes;
	
	public MoveCommand(Location bl, Location el, Boolean t) {
		beginLocation = bl;
		endLocation = el;
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
		String verb = (takes) ? " Takes on " : " Moves to ";
		return "Piece at " + beginLocation + verb + endLocation;
		
	}

	@Override
	public void execute(String[][] board) {
		// TODO Auto-generated method stub
		
	}
	
}
