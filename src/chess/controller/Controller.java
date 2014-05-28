package chess.controller;

import java.io.File;
import java.util.ArrayDeque;
import chess.model.board.ChessBoard;
import chess.model.commands.IExecutable;
import chess.model.customExceptions.InvalidMoveException;

public class Controller {

	private ChessBoard board;
	private FileIO f;
	
	private boolean isLightTurn = true;
	
	public Controller(String filePath) {
		f = new FileIO();
	}
	
	private void executeFile(ArrayDeque<IExecutable> list, boolean withStep) {
		while (!list.isEmpty()) {
			try {
				IExecutable i = list.poll();
				i.execute(board, isLightTurn);
				if(withStep) {
					System.out.println(i);
					board.displayBoard();
				}
				isLightTurn = !isLightTurn;
			} catch (InvalidMoveException e) {
				System.out.println("Invalid Move: " + e.getMessage());
			}
		}
	}
	
	private void setup() {
		System.out.println("New Game");
		board = new ChessBoard();
		isLightTurn = true;
		executeFile(f.parseFile(new File("scripts/setup")), false);
	}

	public void play() {
		setup();
		board.displayBoard();
		executeFile(f.parseFile(new File("scripts/test")), true);
	}
}
