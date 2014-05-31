package chess.model.commands;

import java.util.HashSet;

import chess.model.board.ChessBoard;
import chess.model.board.Location;
import chess.model.customExceptions.InvalidCommandException;
import chess.model.pieces.ChessPiece;
import chess.model.pieces.King;
import chess.model.pieces.Rook;

public class CastleCommand implements IExecutable {
	
	private MoveCommand kingMove;
	private MoveCommand rookMove;
	private String turnString;
	
	public CastleCommand(MoveCommand m1, MoveCommand m2) {
		this.kingMove = m1;
		this.rookMove = m2;
	}

	public String toString() {
		return turnString + " Castles " + ((kingMove.getEndLocation().getColumnIndex() == 6)? "Short" : "Long");
	}

	@Override
	public void execute(ChessBoard board, boolean isLightTurn) throws InvalidCommandException {
		
		HashSet<Integer> tempSet = new HashSet<Integer>();
		tempSet.add(kingMove.getBeginLocation().getRowIndex());
		tempSet.add(kingMove.getEndLocation().getRowIndex());
		tempSet.add(rookMove.getBeginLocation().getRowIndex());
		tempSet.add(rookMove.getEndLocation().getRowIndex());
		
		ChessPiece king = board.getPieceAt(kingMove.getBeginLocation());
		ChessPiece rook = board.getPieceAt(rookMove.getBeginLocation());
		
		if(tempSet.size() != 1) {
			throw new InvalidCommandException("Invalid Castle Command");
		}
		else if(king == null || rook == null || king.hasMoved() || rook.hasMoved()) {
			throw new InvalidCommandException("The King and/or Rook cannot have moved in order to Castle.");
		}
		else if(king.isLight() != isLightTurn || rook.isLight() != isLightTurn) {
			throw new InvalidCommandException("It is " + ((isLightTurn)? "Light" : "Dark") + "'s turn, that piece is not " + ((isLightTurn)? "Light" : "Dark"));
		}
		else if(!isPathClear(kingMove.getBeginLocation().getRowIndex(), board)) {
			throw new InvalidCommandException("There can be no pieces between the King and Rook to Castle");
		}
		else {
			turnString = (isLightTurn)? "Light" : "Dark";
			board.setPieceAt(board.removePieceAt(kingMove.getBeginLocation()), kingMove.getEndLocation());
			board.setPieceAt(board.removePieceAt(rookMove.getBeginLocation()), rookMove.getEndLocation());
			board.getPieceAt(kingMove.getEndLocation()).moved();
			board.getPieceAt(rookMove.getEndLocation()).moved();
		}
	}
	
	private boolean isPathClear(int kingRow, ChessBoard board) {
		
		boolean clear = (kingMove.getEndLocation().getColumnIndex() == 6)? isShortCastleClear(kingRow, board) : isLongCastleClear(kingRow, board);
		return clear;
	}
	
	private boolean isShortCastleClear(int kingRow, ChessBoard board) {
		boolean clear = true;
		
		for(int i = 5; i <= 6 && clear; i++) {
			if(board.getPieceAt(new Location(i, kingRow)) != null) {
				clear = false;
			}
		}
		return clear;
	}
	
	private boolean isLongCastleClear(int kingRow, ChessBoard board) {
		boolean clear = true;
		
		for(int i = 3; i >= 1 && clear; i--) {
			if(board.getPieceAt(new Location(i, kingRow)) != null) {
				clear = false;
			}
		}
		return clear;
	}
	
}
