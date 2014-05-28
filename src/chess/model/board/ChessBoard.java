package chess.model.board;

import chess.model.pieces.ChessPiece;

public class ChessBoard {
	
	private ChessPiece[][] boardArray;
	public static final int ROWS = 8;
	public static final int COLUMNS = 8;
	public static final int MIN_INDEX = 0;
	public static final int MAX_INDEX = 7;
	
	
	public ChessBoard() {
		boardArray  = new ChessPiece[COLUMNS][ROWS];
		
	}

	public void displayBoard() {
		
		for (int i = MAX_INDEX; i >= MIN_INDEX; i--) {
			
			System.out.print(i+1 + " ");
			for (int j = MIN_INDEX; j < boardArray[i].length; j++) {
				
				String s = (boardArray[j][i] == null)? "-" : boardArray[j][i].toString();

				if (j == 7) {
					System.out.println("| " + s + " |");
				}
				else {
					System.out.print("| " + s + " ");
				}
				
			}
		}
		System.out.println("    A   B   C   D   E   F   G   H");
		System.out.println();
	}
	
	public ChessPiece getPieceAt(Location l) {
		return boardArray[l.getColumnIndex()][l.getRowIndex()];
	}
	
	public ChessPiece removePieceAt(Location l) {
		ChessPiece c = boardArray[l.getColumnIndex()][l.getRowIndex()];
		boardArray[l.getColumnIndex()][l.getRowIndex()] = null;
		return c;	
	}
	
	public void setPieceAt(ChessPiece p, Location l) {
		boardArray[l.getColumnIndex()][l.getRowIndex()] = p;
	}
}
