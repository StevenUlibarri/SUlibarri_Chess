package chess;

import java.io.File;

import chess.controller.FileIO;


public class Chess {

	public static void main(String[] args) {
		FileIO f = new FileIO();
		
		f.parseFile(new File(args[0]));
	}

}
