package chess.model.pieces;

public abstract class ChessPiece {
	
	protected String pieceType;
	protected char boardChar;
	private boolean isLight;
	
	public ChessPiece(boolean isLight) {
		this.isLight = isLight;
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

	public String toString() {
		String s = Character.toString(boardChar);
		
		return (isLight)? s : s.toLowerCase();
	}
	
}
