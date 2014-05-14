package model.commands;

public class PlaceCommand {
	
	private String piece;
	private char color;
	private Location placeLocation;
	
	public PlaceCommand(String p, char c, Location loc) {
		piece = p;
		color = c;
		placeLocation = loc;
	}

	public String getPiece() {
		return piece;
	}

	public void setPiece(String piece) {
		this.piece = piece;
	}

	public char getColor() {
		return color;
	}

	public void setColor(char color) {
		this.color = color;
	}

	public Location getPlaceLocation() {
		return placeLocation;
	}

	public void setPlaceLocation(Location placeLocation) {
		this.placeLocation = placeLocation;
	}
	
	public String toString() {
		return color + " " + piece + " placed at " + placeLocation;
	}
	
	
}
