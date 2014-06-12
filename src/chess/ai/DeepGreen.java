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
	
	private int alphaBetaMax(boolean isLight, int alpha, int beta, int depthLeft, ChessBoard board) {
		
		if(board.isMate() || depthLeft == 0) {
			
		}
		
	}
}


//function alphabeta(node, depth, α, β, maximizingPlayer)
//02      if depth = 0 or node is a terminal node
//03          return the heuristic value of node
//04      if maximizingPlayer
//05          for each child of node
//06              α := max(α, alphabeta(child, depth - 1, α, β, FALSE))
//07              if β ≤ α
//08                  break (* β cut-off *)
//09          return α
//10      else
//11          for each child of node
//12              β := min(β, alphabeta(child, depth - 1, α, β, TRUE))
//13              if β ≤ α
//14                  break (* α cut-off *)
//15          return β


//int alphaBetaMax( int alpha, int beta, int depthleft ) {
//	   if ( depthleft == 0 ) return evaluate();
//	   for ( all moves) {
//	      score = alphaBetaMin( alpha, beta, depthleft - 1 );
//	      if( score >= beta )
//	         return beta;   // fail hard beta-cutoff
//	      if( score > alpha )
//	         alpha = score; // alpha acts like max in MiniMax
//	   }
//	   return alpha;
//	}
//	 
//	int alphaBetaMin( int alpha, int beta, int depthleft ) {
//	   if ( depthleft == 0 ) return -evaluate();
//	   for ( all moves) {
//	      score = alphaBetaMax( alpha, beta, depthleft - 1 );
//	      if( score <= alpha )
//	         return alpha; // fail hard alpha-cutoff
//	      if( score < beta )
//	         beta = score; // beta acts like min in MiniMax
//	   }
//	   return beta;
//	}