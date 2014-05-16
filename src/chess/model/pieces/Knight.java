package chess.model.pieces;

public class Knight extends ChessPiece {

	public Knight(boolean isLight) {
		super(isLight);
		this.pieceType = "Kinght";
		this.boardChar = 'N';
	}
}
