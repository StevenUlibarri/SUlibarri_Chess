package chess.model.pieceFactories;

import chess.model.pieces.Bishop;
import chess.model.pieces.ChessPiece;

public class BishopFactory implements IPieceFactory {

	@Override
	public ChessPiece getInstance(Boolean isLight) {
		
		return new Bishop(isLight);
	}

}
