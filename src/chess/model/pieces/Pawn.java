package chess.model.pieces;

public class Pawn  extends ChessPiece {

	public Pawn(boolean isLight) {
		super(isLight);
		this.pieceType = "Pawn";
		this.boardChar = 'P';
	}

}
