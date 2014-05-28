package chess.model.pieces;

import java.util.ArrayList;
import chess.model.board.ChessBoard;
import chess.model.board.Location;

public class Pawn  extends ChessPiece {
	
	private int colorModifier;

	public Pawn(boolean isLight) {
		super(isLight);
		this.pieceType = "Pawn";
		this.boardChar = 'P';
		colorModifier = (isLight)? 1 : -1;
	}

	@Override
	public ArrayList<Location> getValidMoves(Location l, ChessBoard b) {
		
		ArrayList<Location> validMoveList = new ArrayList<Location>();
		
		Location basicMove = getBasicMove(l, b);
		
		if (basicMove != null) {
			validMoveList.add(basicMove);
			if (!super.hasMoved()) {
				validMoveList.add(new Location(l.getColumnIndex(), l.getRowIndex() + 2*colorModifier));
			}
		}
		validMoveList.addAll(getAttackingMoves(l, b));
		return validMoveList;
	}
	
	private Location getBasicMove(Location l, ChessBoard b) {
		Location temp = new Location(l.getColumnIndex(), l.getRowIndex() + 1*colorModifier);
		
		if (b.getPieceAt(temp) != null) {
			temp = null;
		}
		
		return temp;
	}
	
	private ArrayList<Location> getAttackingMoves(Location l, ChessBoard b) {
		ArrayList<Location> atkMoves = new ArrayList<Location>();
		
		for (int i = ChessBoard.MIN_INDEX; i < ChessBoard.ROWS; i++) {
			for (int j = ChessBoard.MIN_INDEX; j < ChessBoard.COLUMNS; j++) {
				
				if ((i - l.getColumnIndex() == -1 || i - l.getColumnIndex() == 1) && j-l.getRowIndex() == 1*colorModifier) {
					Location temp = new Location(i,j);
					
					if (b.getPieceAt(temp) != null) {
						if (b.getPieceAt(temp).isLight() != super.isLight()) {
							atkMoves.add(temp);
						}
					}
				}
				
			}
		}
		return atkMoves;
	}
}
