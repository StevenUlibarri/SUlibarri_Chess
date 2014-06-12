package chess.model.pieces;

import java.util.ArrayList;

import chess.model.board.ChessBoard;
import chess.model.board.Location;

public class Bishop extends ChessPiece {

	public Bishop(boolean isLight) {
		super(isLight);
		this.pieceType = "Bishop";
		this.boardChar = 'B';
		this.pieceValue = 3;
	}

	@Override
	public ArrayList<Location> getValidMoves(Location l, ChessBoard b) {
		
		return super.getBishopMoves(l, b);
	}
}
