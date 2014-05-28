package chess.model.pieceFactories;

import java.util.HashMap;

import chess.model.pieces.*;

public class PieceFactory {
	
	
	private static final HashMap<String, IPieceFactory> pieceMap = new HashMap<String, IPieceFactory>();
	
	static {
		pieceMap.put("k", ((isLight) -> {return new King(isLight);}));
		pieceMap.put("q", ((isLight) -> {return new Queen(isLight);}));
		pieceMap.put("b", ((isLight) -> {return new Bishop(isLight);}));
		pieceMap.put("n", ((isLight) -> {return new Knight(isLight);}));
		pieceMap.put("r", ((isLight) -> {return new Rook(isLight);}));
		pieceMap.put("p", ((isLight) -> {return new Pawn(isLight);}));
	}
	
	public static ChessPiece get(String c, boolean isLight) {
		return pieceMap.get(c).getInstance(isLight);
	}
	
	private interface IPieceFactory {
		public ChessPiece getInstance(Boolean isLight);
	}
}