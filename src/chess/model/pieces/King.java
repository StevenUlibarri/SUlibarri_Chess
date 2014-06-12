package chess.model.pieces;

import java.util.ArrayList;

import chess.model.board.ChessBoard;
import chess.model.board.Location;

public class King extends ChessPiece {

	public King(boolean isLight) {
		super(isLight);
		this.pieceType = "King";
		this.boardChar = 'K';
		this.pieceValue = 0;
	}

	@Override
	public ArrayList<Location> getValidMoves(Location l, ChessBoard b) {
		ArrayList<Location> moves = new ArrayList<Location>();
		
		for (int i = ChessBoard.MIN_INDEX; i < ChessBoard.ROWS; i++) {
			for (int j = ChessBoard.MIN_INDEX; j < ChessBoard.COLUMNS; j++) {
				
				int changeX = Math.abs(l.getColumnIndex() - i);
				int changeY = Math.abs(l.getRowIndex() - j);
				
				if ((changeX + changeY == 2 && l.getColumnIndex() != i && l.getRowIndex() != j) || changeX + changeY == 1) {
					Location temp = new Location(i, j);
					
					if((b.getPieceAt(temp) != null && b.getPieceAt(temp).isLight() != super.isLight()) || b.getPieceAt(temp) == null) {
						moves.add(temp);
					}
				}
			}
		}
		
		return moves;
	}
}
