package chess.model.commands;

import chess.model.board.Location;
import chess.model.pieces.ChessPiece;

public class PlaceCommand implements Executable {
	
	private ChessPiece piece;
	private Location placeLocation;
	
	public PlaceCommand(ChessPiece p, Location loc) {
		piece = p;
		placeLocation = loc;
	}

	public ChessPiece getPiece() {
		return piece;
	}

	public Location getPlaceLocation() {
		return placeLocation;
	}
	
	public String toString() {
		String pieceColor = (piece.isLight()) ? "Light" : "Dark";
		return pieceColor + " " + piece.getPieceType() + " placed at " + placeLocation;
	}

	@Override
	public void execute(String[][] board) {
		// TODO Auto-generated method stub
		
	}
	
	
}
