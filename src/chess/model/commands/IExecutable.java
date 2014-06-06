package chess.model.commands;

import chess.model.board.ChessBoard;
import chess.model.customExceptions.InvalidCommandException;

public interface IExecutable {
	
	public void execute(ChessBoard board, boolean isLightTurn) throws InvalidCommandException;
	public void executeLite(ChessBoard board, boolean isLightTurn);
	public String getSelectString();

}
