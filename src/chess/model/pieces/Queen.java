package chess.model.pieces;

import java.util.ArrayList;

import chess.model.board.ChessBoard;
import chess.model.board.Location;

public class Queen extends ChessPiece {

	public Queen(boolean isLight) {
		super(isLight);
		this.pieceType = "Queen";
		this.boardChar = 'Q';
		this.pieceValue = 9;
	}

	@Override
	public ArrayList<Location> getValidMoves(Location l, ChessBoard b) {
		
		return super.getQueenMoves(l, b);
		
	}
}
