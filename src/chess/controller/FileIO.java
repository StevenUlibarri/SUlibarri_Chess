package chess.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import chess.model.board.Location;
import chess.model.commands.CastleCommand;
import chess.model.commands.Executable;
import chess.model.commands.MoveCommand;
import chess.model.commands.PlaceCommand;
import chess.model.pieces.PieceDictionary;

public class FileIO {
	
	private Pattern moveCommand = Pattern.compile("^(?<Column1>[a-h])(?<Row1>[1-8]) (?<Column2>[a-h])(?<Row2>[1-8])\\*?$");
	private Pattern placeCommand = Pattern.compile("^(?<Piece>[kqbnrp])(?<Color>[ld])(?<Column>[a-h])(?<Row>[1-8])$");
	private Pattern castleCommand = Pattern.compile("^(?<Column1>[a-h])(?<Row1>[1-8]) (?<Column2>[a-h])(?<Row2>[1-8]) (?<Column3>[a-h])(?<Row3>[1-8]) (?<Column4>[a-h])(?<Row4>[1-8])$");
	
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
				
				Executable e;
				
				if (placeMatch.matches()) {
					e = new PlaceCommand(
							PieceDictionary.get(placeMatch.group("Piece"), placeMatch.group("Color").charAt(0) == 'l'),
							new Location(
									placeMatch.group("Column").charAt(0),
									Integer.parseInt(placeMatch.group("Row"))));
					
					System.out.println(e);
				}
				else if (moveMatch.matches()) {
					e = new MoveCommand(
							new Location(
									moveMatch.group("Column1").charAt(0),
									Integer.parseInt(moveMatch.group("Row1"))),
							new Location(
									moveMatch.group("Column2").charAt(0),
									Integer.parseInt(moveMatch.group("Row2"))),
							currentLine.contains("*"));
					
					System.out.println(e);
				}
				else if (castleMatch.matches()) {
					e = new CastleCommand(
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
					
					System.out.println(e);
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
}
