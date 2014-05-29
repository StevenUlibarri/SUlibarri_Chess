package chess.model.commands;

import chess.model.board.ChessBoard;

public class CastleCommand implements IExecutable {
	
	private MoveCommand move1;
	private MoveCommand move2;
	
	public CastleCommand(MoveCommand m1, MoveCommand m2) {
		this.move1 = m1;
		this.move2 = m2;
	}

	public String toString() {
		return move1 + " and " + move2;
	}

	@Override
	public void execute(ChessBoard board, boolean isLightTurn) {
		
	}
	
}
