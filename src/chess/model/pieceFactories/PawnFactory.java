package chess.model.pieceFactories;

import chess.model.pieces.ChessPiece;
import chess.model.pieces.Pawn;

public class PawnFactory implements IPieceFactory {

	@Override
	public ChessPiece getInstance(Boolean isLight) {
		
		return new Pawn(isLight);
	}

}
