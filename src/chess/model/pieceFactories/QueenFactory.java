package chess.model.pieceFactories;

import chess.model.pieces.ChessPiece;
import chess.model.pieces.Queen;

public class QueenFactory implements IPieceFactory {

	@Override
	public ChessPiece getInstance(Boolean isLight) {
		
		return new Queen(isLight);
	}

}
