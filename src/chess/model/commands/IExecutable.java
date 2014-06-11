package chess.model.commands;

import chess.model.board.ChessBoard;
import chess.model.board.Location;
import chess.model.customExceptions.InvalidCommandException;

public interface IExecutable {
	
	public void execute(ChessBoard board) throws InvalidCommandException;
	public void executeLite(ChessBoard board);
	public String executeAI(ChessBoard board);
	public String getSelectString();
	public Location getDestination();

}
