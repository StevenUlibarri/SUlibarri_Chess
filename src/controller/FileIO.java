package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.commands.CastleCommand;
import model.commands.Location;
import model.commands.MoveCommand;
import model.commands.PlaceCommand;
import model.pieces.PieceDictionary;

public class FileIO {
	
	private Pattern moveCommand = Pattern.compile("(?<Column1>[a-h])(?<Row1>[1-8])\\*? (?<Column2>[a-h])(?<Row2>[1-8])",Pattern.CASE_INSENSITIVE);
	private Pattern placeCommand = Pattern.compile("(?<Piece>[kqbnrp])(?<Color>[ld])(?<Column>[a-h])(?<Row>[1-8])", Pattern.CASE_INSENSITIVE);
	private Pattern castleCommand = Pattern.compile("(?<Column1>[a-h])(?<Row1>[1-8]) (?<Column2>[a-h])(?<Row2>[1-8]) (?<Column3>[a-h])(?<Row3>[1-8]) (?<Column4>[a-h])(?<Row4>[1-8])",
			Pattern.CASE_INSENSITIVE);
	
	BufferedReader in;

	public FileIO() {
		
	}
	
//	public String getInput() {
//		in = new BufferedReader(new InputStreamReader(System.in));
//		return null;
//	}
	
	public void parseFile(File f) {
		
		String currentLine;
		
		try {
			in = new BufferedReader(new FileReader(f));
			while ((currentLine = in.readLine()) != null) {
				
				Matcher moveMatch = moveCommand.matcher(currentLine);
				Matcher placeMatch = placeCommand.matcher(currentLine);
				Matcher castleMatch = castleCommand.matcher(currentLine);
				
				if (placeMatch.matches()) {
					PlaceCommand pc = new PlaceCommand(
							PieceDictionary.get(placeMatch.group("Piece")),
							placeMatch.group("Color").charAt(0),
							new Location(
									placeMatch.group("Column").charAt(0),
									Integer.parseInt(placeMatch.group("Row"))));
					
					System.out.println(pc);
				}
				else if (moveMatch.matches()) {
					MoveCommand mc = new MoveCommand(
							new Location(
									moveMatch.group("Column1").charAt(0),
									Integer.parseInt(moveMatch.group("Row1"))),
							new Location(
									moveMatch.group("Column2").charAt(0),
									Integer.parseInt(moveMatch.group("Row2"))),
							currentLine.contains("*"));
					
					System.out.println(mc);
				}
				else if (castleMatch.matches()) {
					CastleCommand cm = new CastleCommand(
							new MoveCommand(
									new Location(
											castleMatch.group("Column1").charAt(0),
											Integer.parseInt(castleMatch.group("Row1"))),
									new Location(
											castleMatch.group("Column2").charAt(0),
											Integer.parseInt(castleMatch.group("Row2"))),
									false),
							new MoveCommand(
									new Location(
											castleMatch.group("Column3").charAt(0),
											Integer.parseInt(castleMatch.group("Row3"))),
									new Location(
											castleMatch.group("Column4").charAt(0),
											Integer.parseInt(castleMatch.group("Row4"))),
									false));
					
					System.out.println(cm);
				}
				else {
					System.out.println("Invalid Command");
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
