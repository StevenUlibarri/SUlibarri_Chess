package chess.model.pieces;

public class Bishop extends ChessPiece {

	public Bishop(boolean isLight) {
		super(isLight);
		this.pieceType = "Bishop";
		this.boardChar = 'B';
	}

}
