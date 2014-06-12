package chess.model.pieces;

import java.util.ArrayList;

import chess.model.board.ChessBoard;
import chess.model.board.Location;

public class Rook extends ChessPiece {

	public Rook(boolean isLight) {
		super(isLight);
		this.pieceType = "Rook";
		this.boardChar = 'R';
		this.pieceValue = 5;
	}

	@Override
	public ArrayList<Location> getValidMoves(Location l, ChessBoard b) {
		
		return super.getRookMoves(l, b);

	}
}
