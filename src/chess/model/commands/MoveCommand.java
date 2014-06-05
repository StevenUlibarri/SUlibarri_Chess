package chess.model.commands;

import java.util.ArrayList;

import chess.model.board.ChessBoard;
import chess.model.board.Location;
import chess.model.customExceptions.InvalidCommandException;
import chess.model.pieces.ChessPiece;
import chess.model.pieces.King;

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
	public void execute(ChessBoard board, boolean isLightTurn) throws InvalidCommandException {
		
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
		else if(moveAllowsFriendlyCheck(board, isLightTurn)) {
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
			
			upDateCheckStatus(isLightTurn, board);
			if(board.isKinginCheck(!isLightTurn)) {
				checkString = " - " + ((isLightTurn)? "Dark":"Light") + " king in Check!";
			}
		}	
	}
	
	private void upDateCheckStatus(boolean turn, ChessBoard board) {
		board.setKingInCheck(!turn, kingAttacked(board, !turn));
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
	
	private boolean moveAllowsFriendlyCheck(ChessBoard currentBoard, boolean turn) {
		ChessBoard tempBoard = new ChessBoard(currentBoard);
		tempBoard.setPieceAt(tempBoard.removePieceAt(beginLocation), endLocation);
		
		return currentBoard.locationAttacked(currentBoard.getKingLocation(turn), !turn);
	}

	private boolean kingAttacked(ChessBoard board, boolean color) {
		boolean attacked = false;
		
		for (int i = 0; i <= ChessBoard.MAX_INDEX && !attacked; i++) {
			for (int j = 0; j <= ChessBoard.MAX_INDEX && !attacked; j++) {
				Location tempLocation = new Location(i,j);
				ChessPiece tempPiece = board.getPieceAt(tempLocation);
				
				if (tempPiece != null && tempPiece.isLight() != color) {
					
					ArrayList<Location> tempList = tempPiece.getValidMoves(tempLocation, board);
					for (int x = 0; x < tempList.size() && !attacked; x++) {
						ChessPiece pieceToCheck = board.getPieceAt(tempList.get(x));
						if (pieceToCheck != null && pieceToCheck instanceof King && pieceToCheck.isLight() == color) {
							attacked = true;
						}
					}
				}
			}
		}
		return attacked;
	}
}
