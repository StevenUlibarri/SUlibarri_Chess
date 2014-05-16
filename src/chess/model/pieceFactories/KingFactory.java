package chess.model.pieceFactories;

import chess.model.pieces.ChessPiece;
import chess.model.pieces.King;

public class KingFactory implements IPieceFactory {

	public ChessPiece getInstance(Boolean isLight) {
		
		return new King(isLight);
	}

}
