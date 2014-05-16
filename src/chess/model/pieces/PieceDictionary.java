package chess.model.pieces;

import java.util.HashMap;

import chess.model.pieceFactories.*;

public class PieceDictionary {
	
	
	private static final HashMap<String, IPieceFactory> pieceMap = new HashMap<String, IPieceFactory>();
	
	static {
		pieceMap.put("k", new KingFactory());
		pieceMap.put("q", new QueenFactory());
		pieceMap.put("b", new BishopFactory());
		pieceMap.put("n", new KnightFactory());
		pieceMap.put("r", new RookFactory());
		pieceMap.put("p", new PawnFactory());
	}
	
	public static ChessPiece get(String c, boolean isLight) {
		return pieceMap.get(c).getInstance(isLight);
	}

}
