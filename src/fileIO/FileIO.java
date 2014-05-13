package fileIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileIO {
	
	public final String moveCommandPattern = "([a-h][1-8] [a-h][1-8]\\*?)( [a-h][1-8] [a-h][1-8])?";
	public final String placeCommandPattern = "[kqbnrp][dl][a-h][1-8]";
	
	BufferedReader in;

	public FileIO() {
		
	}
	
	public String getInput() {
		in = new BufferedReader(new InputStreamReader(System.in));
		return null;
	}
	
	public void parseFile(File f) {
		
		String currentLine;
		
		try {
			in = new BufferedReader(new FileReader(f));
			while ((currentLine = in.readLine()) != null) {
				
				if (currentLine.matches(moveCommandPattern)) {
					System.out.println(commandToEnglish(currentLine.split(" ")));
				}
				else if (currentLine.matches(placeCommandPattern)) {
					System.out.println(commandToEnglish(currentLine));
				}
				else {
					System.out.println("invalid command");
				}
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String commandToEnglish(String str) {
		
		char[] cArr = str.toCharArray();
		
		String action = "placed";
		String piece = charToPiece(cArr[0]);
		String color = charToColor(cArr[1]);
		
		return color + " " + piece + " " + action + " at " + cArr[2] + cArr[3];
	}
	
	private String commandToEnglish(String[] str) {
		
		String s = null;
		
		boolean takes = str[1].contains("*");
		str[1] = str[1].replace("*", "");
		
		String piece = "piece";
		String takepiece = "piece";
		String action = takes? "takes" : "moves";
		String prep = takes? takepiece + " on" : "to";
		
	
		s = piece + " at " + str[0] + " " + action + " " + prep + " " + str[1];
		
		if (str.length > 2) {
			s = s + " and " + piece + " at " + str[2] + " " + action + " " + prep + " " + str[3];
		}
		
		return s;
	}
	
	private String charToPiece(char c) {
		
		String piece = null;
		
		switch (c) {
		case 'k':
			piece = "king";
			break;
		case 'q':
			piece = "queen";
			break;
		case 'b':
			piece = "bishop";
			break;
		case 'n':
			piece = "knight";
			break;
		case 'r':
			piece = "rook";
			break;
		case 'p':
			piece = "pawn";
			break;
		default:
			piece = "seomething went terribly wrong";
			break;
		}
		
		return piece;
	}

	private String charToColor(char c) {
		
		String color = null;
		
		switch(c) {
		case 'l':
			color = "light";
			break;
		case 'd':
			color = "dark";
			break;
		default:
			color = "something has gone terribly wrong";
		}
		
		return color;
	}
}
