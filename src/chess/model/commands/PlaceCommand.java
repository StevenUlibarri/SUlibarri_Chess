package chess.model.commands;

import chess.model.board.ChessBoard;
import chess.model.board.Location;
import chess.model.pieces.ChessPiece;

public class PlaceCommand implements IExecutable {
	
	private ChessPiece piece;
	private Location location;
	
	public PlaceCommand(ChessPiece p, Location loc) {
		piece = p;
		location = loc;
	}
	
	public String toString() {
		String pieceColor = (piece.isLight()) ? "Light" : "Dark";
		return pieceColor + " " + piece.getPieceType() + " placed at " + location;
	}

	@Override
	public void execute(ChessBoard board, boolean isLightTurn) {
		
		board.setPieceAt(piece, location);
		
	}

	@Override
	public String getSelectString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void executeLite(ChessBoard board, boolean isLightTurn) {
		// TODO Auto-generated method stub
		
	}
	
	
}
