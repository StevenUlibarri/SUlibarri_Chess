package chess.model.pieces;

public class Queen extends ChessPiece {

	public Queen(boolean isLight) {
		super(isLight);
		this.pieceType = "Queen";
		this.boardChar = 'Q';
	}

}
