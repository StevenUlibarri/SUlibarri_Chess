package chess.model.pieces;

import java.util.HashMap;

public class PieceDictionary {
	
	
	private static final HashMap<String, String> pieceMap = new HashMap<String, String>();
	
	static {
		pieceMap.put("k", "King");
		pieceMap.put("q", "Queen");
		pieceMap.put("b", "Bishop");
		pieceMap.put("n", "Knight");
		pieceMap.put("r", "Rook");
		pieceMap.put("p", "Pawn");
	}
	
	public static String get(String c) {
		return pieceMap.get(c);
	}
	

}
