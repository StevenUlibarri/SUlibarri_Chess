package chess.model.pieceFactories;

import chess.model.pieces.ChessPiece;

public interface IPieceFactory {
	
	public ChessPiece getInstance(Boolean isLight);

}
