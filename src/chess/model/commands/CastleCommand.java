package chess.model.commands;

import java.util.HashSet;

import chess.model.board.ChessBoard;
import chess.model.customExceptions.InvalidCommandException;
import chess.model.pieces.ChessPiece;

public class CastleCommand implements IExecutable {
	
	private MoveCommand kingMove;
	private MoveCommand rookMove;
	private String turnString = "";
	private String checkString = "";
	
	public CastleCommand(MoveCommand m1, MoveCommand m2) {
		this.kingMove = m1;
		this.rookMove = m2;
	}

	public String toString() {
		return turnString + " Castles " + ((kingMove.getEndLocation().getColumnIndex() == 6)? "Short" : "Long") + checkString;
	}

	@Override
	public void execute(ChessBoard board, boolean isLightTurn) throws InvalidCommandException {
		
		ChessPiece king = board.getPieceAt(kingMove.getBeginLocation());
		ChessPiece rook = board.getPieceAt(rookMove.getBeginLocation());
		
		if(!rowsMatch()) {
			throw new InvalidCommandException("Invalid Castle Command");
		}
		else if(board.isKinginCheck(isLightTurn)) {
			throw new InvalidCommandException("Cannot castle while in check");
		}
		else if(king == null || rook == null || king.hasMoved() || rook.hasMoved()) {
			throw new InvalidCommandException("The King and/or Rook cannot have moved in order to Castle.");
		}
		else if(king.isLight() != isLightTurn || rook.isLight() != isLightTurn) {
			throw new InvalidCommandException("It is " + ((isLightTurn)? "Light" : "Dark") + "'s turn, that piece is not " + ((isLightTurn)? "Light" : "Dark"));
		}
		else if(!board.isCastlePathClear(kingMove.getBeginLocation(), kingMove.getEndLocation())) {
			throw new InvalidCommandException("When Castling the spaces between the Rook and King cannot be occupied or attacked");
		}
		else {
			turnString = (isLightTurn)? "Light" : "Dark";
			board.setPieceAt(board.removePieceAt(kingMove.getBeginLocation()), kingMove.getEndLocation());
			board.setPieceAt(board.removePieceAt(rookMove.getBeginLocation()), rookMove.getEndLocation());
			board.getPieceAt(kingMove.getEndLocation()).moved();
			board.getPieceAt(rookMove.getEndLocation()).moved();
			
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
	
	private boolean rowsMatch() {
		HashSet<Integer> tempSet = new HashSet<Integer>();
		tempSet.add(kingMove.getBeginLocation().getRowIndex());
		tempSet.add(kingMove.getEndLocation().getRowIndex());
		tempSet.add(rookMove.getBeginLocation().getRowIndex());
		tempSet.add(rookMove.getEndLocation().getRowIndex());
		
		return tempSet.size() == 1;
	}

	@Override
	public String getSelectString() {
		return "Castle " + ((kingMove.getEndLocation().getColumnIndex() == 6)? "Short" : "Long");
	}

	@Override
	public void executeLite(ChessBoard board, boolean isLightTurn) {
		turnString = (isLightTurn)? "Light" : "Dark";
		board.setPieceAt(board.removePieceAt(kingMove.getBeginLocation()), kingMove.getEndLocation());
		board.setPieceAt(board.removePieceAt(rookMove.getBeginLocation()), rookMove.getEndLocation());
		board.getPieceAt(kingMove.getEndLocation()).moved();
		board.getPieceAt(rookMove.getEndLocation()).moved();
		
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
