package chess.model.pieces;

public class King extends ChessPiece {

	public King(boolean isLight) {
		super(isLight);
		this.pieceType = "King";
		this.boardChar = 'K';
	}

}
