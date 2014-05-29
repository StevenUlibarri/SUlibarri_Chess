package chess.model.commands;

import chess.model.board.ChessBoard;
import chess.model.board.Location;
import chess.model.customExceptions.InvalidCommandException;
import chess.model.pieces.ChessPiece;

public class MoveCommand implements IExecutable{
	
	private Location beginLocation;
	private Location endLocation;
	private boolean takes;
	private String movingPieceString;
	private String pieceToTake;
	
	public MoveCommand(Location bl, Location el, Boolean t) {
		beginLocation = bl;
		endLocation = el;
		takes = t;
	}

	@Override
	public void execute(ChessBoard board, boolean isLightTurn) throws InvalidCommandException {
		
		ChessPiece movingPiece = board.getPieceAt(beginLocation);
		
		if(movingPiece == null) {
			throw new InvalidCommandException("There is no Piece at the Location");
		}
		else if(movingPiece.isLight() != isLightTurn) {
			throw new InvalidCommandException("It is " + ((isLightTurn)? "Light" : "Dark") + "'s turn, that piece is not " + ((isLightTurn)? "Light" : "Dark"));
		}
		else if(board.getPieceAt(endLocation) != null && (movingPiece.isLight() == board.getPieceAt(endLocation).isLight())) {
			throw new InvalidCommandException("You cannot take your own Piece!");
		}
		else if(!movingPiece.isValidMove(beginLocation, endLocation, board)) {
			throw new InvalidCommandException("That Piece cannot move to that Location");
		}
		else {
			
			movingPieceString = movingPiece.toTextString();
			if (board.getPieceAt(endLocation) != null) {
				takes = true;
				pieceToTake = board.getPieceAt(endLocation).toTextString();
			}
			else {
				takes = false;
			}
			
			board.setPieceAt(board.removePieceAt(beginLocation), endLocation);
			board.getPieceAt(endLocation).moved();
		}	
	}
	
	public Location getBeginLocation() {
		return beginLocation;
	}
	
	public Location getEndLocation() {
		return endLocation;
	}
	
	public String toString() {
		String verb = (takes) ? " Takes " + pieceToTake + " on " : " Moves to ";
		return movingPieceString + " at " + beginLocation + verb + endLocation;
		
	}
}
