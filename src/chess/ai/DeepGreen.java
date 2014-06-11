package chess.ai;

import java.util.ArrayList;
import java.util.Random;

import chess.model.board.ChessBoard;
import chess.model.board.Location;
import chess.model.commands.IExecutable;
import chess.model.commands.MoveCommand;
import chess.model.pieces.ChessPiece;

public class DeepGreen {
	boolean isLight;
	
	public DeepGreen(boolean isLight)
	{
		this.isLight = isLight;
	}
	
	public IExecutable makeMove(ChessBoard board)
	{
		IExecutable move = null;
		ArrayList<Location> piecesWithMoves = board.getPiecesWithValidMoves(isLight);
		
		Random rand = new Random();
		Location pieceLocation = piecesWithMoves.get(rand.nextInt(piecesWithMoves.size()));
		ChessPiece chosenPiece = board.getPieceAt(pieceLocation);
		
		ArrayList<Location> movesForPiece = chosenPiece.getValidMoves(pieceLocation, board);
		movesForPiece = board.mateFilter(pieceLocation, movesForPiece);
		
		return new MoveCommand(pieceLocation, movesForPiece.get(rand.nextInt(movesForPiece.size())), false);
	}
}
