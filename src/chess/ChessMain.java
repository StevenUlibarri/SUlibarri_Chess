package chess;

import chess.controller.Controller;


public class ChessMain {

	public static void main(String[] args) {
		
		Controller c = new Controller(args[0]);
		c.play();
	}

}
