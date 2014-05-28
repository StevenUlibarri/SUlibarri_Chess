package chess.model.pieces;

import java.util.ArrayList;
import chess.model.board.ChessBoard;
import chess.model.board.Location;

public abstract class ChessPiece {
	
	protected String pieceType;
	protected char boardChar;
	private boolean isLight;
	private boolean hasMoved;
	
	public ChessPiece(boolean isLight) {
		this.isLight = isLight;
		hasMoved = false;
	}
	
	public String getPieceType() {
		return pieceType;
	}
	
	public char getBoardChar() {
		return boardChar;
	}

	public boolean isLight() {
		return isLight;
	}
	
	public boolean hasMoved() {
		return hasMoved;
	}
	
	public void moved() {
		hasMoved = true;
	}

	public String toString() {
		String s = Character.toString(boardChar);
		
		return (isLight)? s : s.toLowerCase();
	}
	
	public String toTextString() {
		return ((isLight)? "Light" : "Dark") + " " + pieceType;
	}
	
	public abstract ArrayList<Location> getValidMoves(Location l, ChessBoard b);
	
	public boolean isValidMove(Location beginLocation, Location endLocation, ChessBoard b) {
		return findLocationInList(endLocation, getValidMoves(beginLocation, b));
	};
	
	protected boolean findLocationInList(Location l, ArrayList<Location> validMoveList) {
		boolean valid = false;
		for (int i = 0; i < validMoveList.size() && !valid; i++) {
			if (l.getColumnIndex() == validMoveList.get(i).getColumnIndex() && l.getRowIndex() == validMoveList.get(i).getRowIndex()) {
				valid = true;
			}
		}
		return valid;
	}
	
	protected ArrayList<Location> getBishopMoves(Location l, ChessBoard b) {
		ArrayList<Location> moves = new ArrayList<Location>();

		moves.addAll(getMovesInDirection(1, 1, l, b));
		moves.addAll(getMovesInDirection(1, -1, l, b));
		moves.addAll(getMovesInDirection(-1, 1, l, b));
		moves.addAll(getMovesInDirection(-1, -1, l, b));
		
		return moves;
	}
	
	protected ArrayList<Location> getRookMoves(Location l, ChessBoard b) {
		ArrayList<Location> moves = new ArrayList<Location>();
		
		moves.addAll(getMovesInDirection(1, 0, l, b));
		moves.addAll(getMovesInDirection(0, 1, l, b));
		moves.addAll(getMovesInDirection(-1, 0, l, b));
		moves.addAll(getMovesInDirection(0, -1, l, b));
		
		return moves;
		
	}

	public ArrayList<Location> getQueenMoves(Location l, ChessBoard b) {
		ArrayList<Location> moves = new ArrayList<Location>();
		
		moves.addAll(getBishopMoves(l, b));
		moves.addAll(getRookMoves(l, b));
		
		return moves;
	}
	
	private ArrayList<Location> getMovesInDirection(int changeX, int changeY, Location l, ChessBoard b) {
		ArrayList<Location> moves = new ArrayList<Location>();
		
		int x = l.getColumnIndex();
		int y = l.getRowIndex();
		
		boolean blocked = false;
		while (onBoard(x += changeX) && onBoard(y += changeY) && !blocked) {
			Location temp = new Location(x,y);
			if(b.getPieceAt(temp) != null) {
				if(b.getPieceAt(temp).isLight() != isLight) {
					moves.add(temp);
				}
				blocked = true;
			}
			else {
				moves.add(temp);
			}
		}
		return moves;
	}

	private boolean onBoard(int num) {
		return (num >= ChessBoard.MIN_INDEX && num <= ChessBoard.MAX_INDEX);
	}
}
