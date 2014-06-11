package chess;

import chess.controller.Controller;


public class ChessMain {

	public static void main(String[] args) {
		
		Controller c = new Controller();
//		c.playConsole();
//		c.playGui();
//		c.playFile();
		
		c.playJudge((args[0].equals("white") ? true : false));
//		ScratchCode s = new ScratchCode();
	}

}
