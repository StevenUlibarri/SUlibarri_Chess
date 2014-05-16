package chess.model.pieceFactories;

import chess.model.pieces.ChessPiece;
import chess.model.pieces.Knight;

public class KnightFactory implements IPieceFactory {

	@Override
	public ChessPiece getInstance(Boolean isLight) {
		
		return new Knight(isLight);
	}

}
