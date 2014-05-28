package chess.model.commands;

import chess.model.board.ChessBoard;
import chess.model.customExceptions.InvalidMoveException;

public interface IExecutable {
	
	public void execute(ChessBoard board, boolean isLightTurn) throws InvalidMoveException;

}