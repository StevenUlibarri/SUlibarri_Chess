package chess.model.pieceFactories;

import chess.model.pieces.ChessPiece;
import chess.model.pieces.Rook;

public class RookFactory implements IPieceFactory {

	@Override
	public ChessPiece getInstance(Boolean isLight) {
		
		return new Rook(isLight);
	}

}
