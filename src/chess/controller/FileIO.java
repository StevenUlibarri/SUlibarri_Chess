package chess.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import chess.model.board.Location;
import chess.model.commands.CastleCommand;
import chess.model.commands.IExecutable;
import chess.model.commands.MoveCommand;
import chess.model.commands.PlaceCommand;
import chess.model.pieceFactories.PieceFactory;

public class FileIO {
	
	private Pattern moveCommand = Pattern.compile("^(?<Column1>[a-h])(?<Row1>[1-8]) (?<Column2>[a-h])(?<Row2>[1-8])\\*?$");
	private Pattern placeCommand = Pattern.compile("^(?<Piece>[kqbnrp])(?<Color>[ld])(?<Column>[a-h])(?<Row>[1-8])$");
	private Pattern castleCommand = Pattern.compile("^(?<Column1>[e])(?<Row1>[18]) (?<Column2>[cg])(?<Row2>[18]) (?<Column3>[ah])(?<Row3>[18]) (?<Column4>[df])(?<Row4>[18])$");
	
	BufferedReader in;

	public FileIO() {
		
	}
	
	public ArrayDeque<IExecutable> parseFile(File f) {
		String currentLine;
		ArrayDeque<IExecutable> list = new ArrayDeque<IExecutable>();
		
		try {
			in = new BufferedReader(new FileReader(f));
			while ((currentLine = in.readLine()) != null) {
				list.offer(parseCommand(currentLine));
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (NullPointerException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public IExecutable parseCommand(String cmd) {
		IExecutable tempCommand = null;
		
		Matcher moveMatch = moveCommand.matcher(cmd);
		Matcher placeMatch = placeCommand.matcher(cmd);
		Matcher castleMatch = castleCommand.matcher(cmd);
		
		if (placeMatch.matches()) {
			tempCommand = new PlaceCommand(
					PieceFactory.get(placeMatch.group("Piece"), placeMatch.group("Color").charAt(0) == 'l'),
					new Location(
							placeMatch.group("Column").charAt(0),
							Integer.parseInt(placeMatch.group("Row"))));
		}
		else if (moveMatch.matches()) {
			tempCommand = new MoveCommand(
					new Location(
							moveMatch.group("Column1").charAt(0),
							Integer.parseInt(moveMatch.group("Row1"))),
					new Location(
							moveMatch.group("Column2").charAt(0),
							Integer.parseInt(moveMatch.group("Row2"))),
					cmd.contains("*"));
		}
		else if (castleMatch.matches()) {
			tempCommand = new CastleCommand(
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
		}
		return tempCommand;
	}
}
