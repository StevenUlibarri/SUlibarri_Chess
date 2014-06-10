package chess.model.commands;

import chess.model.board.ChessBoard;
import chess.model.board.Location;
import chess.model.customExceptions.InvalidCommandException;
import chess.model.pieces.ChessPiece;
import chess.model.pieces.Pawn;
import chess.model.pieces.Queen;

public class MoveCommand implements IExecutable{
	
	private Location beginLocation;
	private Location endLocation;
	private boolean takes;
	private String movingPieceString;
	private String pieceToTake;
	private String checkString = "";
	
	public MoveCommand(Location bl, Location el, Boolean t) {
		beginLocation = bl;
		endLocation = el;
		takes = t;
	}

	@Override
	public void execute(ChessBoard board) throws InvalidCommandException {
		
		boolean isLightTurn = board.getTurn();
		
		ChessPiece movingPiece = board.getPieceAt(beginLocation);
		ChessPiece endingPiece = board.getPieceAt(endLocation);
		
		if(movingPiece == null) {
			throw new InvalidCommandException("There is no Piece at the Location");
		}
		else if(movingPiece.isLight() != isLightTurn) {
			throw new InvalidCommandException("It is " + ((isLightTurn)? "Light" : "Dark") + "'s turn, that piece is not " + ((isLightTurn)? "Light" : "Dark"));
		}
		else if(endingPiece != null && (movingPiece.isLight() == endingPiece.isLight())) {
			throw new InvalidCommandException("You cannot take your own Piece!");
		}
		else if(!movingPiece.isValidMove(beginLocation, endLocation, board)) {
			throw new InvalidCommandException("That Piece cannot move to that Location");
		}
		else if(board.moveAllowsFriendlyCheck(beginLocation, endLocation, isLightTurn)) {
			throw new InvalidCommandException("That move would leave your king in Check");
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
			
			checkPromotions(isLightTurn, board);
			board.upDateCheckStatus(isLightTurn);
			
			if(board.isKinginCheck(!isLightTurn) && board.isMate(!isLightTurn)) {
				board.setMate(true);
				checkString = " - " + ((isLightTurn)? "Dark":"Light") + " king CheckMate! " + ((isLightTurn)? "Light":"Dark") + " Wins!";
			}
			else if(board.isMate(!isLightTurn)) {
				board.setMate(true);
				checkString = " - " + "StaleMate!";
			}
			else if(board.isKinginCheck(!isLightTurn)) {
				checkString = " - " + ((isLightTurn)? "Dark":"Light") + " king in Check!";
			}
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
		return movingPieceString + " at " + beginLocation + verb + endLocation + checkString;
	}

	@Override
	public String getSelectString() {
		return endLocation.toString();
	}

	@Override
	public void executeLite(ChessBoard board) {
		
		Boolean isLightTurn = board.getTurn();
		
		ChessPiece movingPiece = board.getPieceAt(beginLocation);
		
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
		
		checkPromotions(isLightTurn, board);
		board.upDateCheckStatus(isLightTurn);
		
		if(board.isKinginCheck(!isLightTurn) && board.isMate(!isLightTurn)) {
			board.setMate(true);
			checkString = " - " + ((isLightTurn)? "Dark":"Light") + " king CheckMate! " + ((isLightTurn)? "Light":"Dark") + " Wins!";
		}
		else if(board.isMate(!isLightTurn)) {
			board.setMate(true);
			checkString = " - " + "StaleMate!";
		}
		else if(board.isKinginCheck(!isLightTurn)) {
			checkString = " - " + ((isLightTurn)? "Dark":"Light") + " king in Check!";
		}
	}
	
	private void checkPromotions(boolean color, ChessBoard board) {
		int rowToCheck = (color)? 7:0;
		
		for(int i = 0; i <= ChessBoard.MAX_INDEX; i++) {
			ChessPiece p = board.getPieceAt(new Location(i, rowToCheck));
			if(p != null && p instanceof Pawn) {
				board.setPieceAt(new Queen(color), new Location(i, rowToCheck));
			}
		}
	}

	@Override
	public Location getDestination() {
		return this.endLocation;
	}
}
