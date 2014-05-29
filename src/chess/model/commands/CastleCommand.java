package chess.model.commands;

import chess.model.board.ChessBoard;
import chess.model.customExceptions.InvalidCommandException;
import chess.model.pieces.ChessPiece;
import chess.model.pieces.King;
import chess.model.pieces.Rook;

public class CastleCommand implements IExecutable {
	
	private MoveCommand move1;
	private MoveCommand move2;
	
	public CastleCommand(MoveCommand m1, MoveCommand m2) {
		this.move1 = m1;
		this.move2 = m2;
	}

	public String toString() {
		return move1 + " and " + move2;
	}

	@Override
	public void execute(ChessBoard board, boolean isLightTurn) throws InvalidCommandException {
		
		ChessPiece king = board.getPieceAt(move1.getBeginLocation());
		ChessPiece rook = board.getPieceAt(move2.getBeginLocation());
		
//		if(king == null || !(king instanceof King)) {
//			System.out.println("");
//		}
//		else if(king.isLight() != isLightTurn) {
//			System.out.println("");
//		}
		
//		if(move1.getBeginLocation().getRowIndex() == move1.getEndLocation().getRowIndex() == )
		
	}
	
}
