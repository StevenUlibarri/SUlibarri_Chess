package chess.controller;

import java.io.File;
import java.util.ArrayDeque;
import java.util.ArrayList;

import chess.model.board.ChessBoard;
import chess.model.board.Location;
import chess.model.commands.CastleCommand;
import chess.model.commands.IExecutable;
import chess.model.commands.MoveCommand;
import chess.model.customExceptions.InvalidCommandException;
import chess.model.pieces.King;

public class Controller {

	private ChessBoard board;
	private FileIO f;
	private MyIO m;
	
	private boolean isLightTurn = true;
	
	public Controller() {
		f = new FileIO();
		m = new MyIO();
		
	}
	
	private void executeFile(ArrayDeque<IExecutable> list, boolean withStep, boolean withTurns) {
		while (!list.isEmpty()) {
			try {
				IExecutable i = list.poll();
				i.execute(board, isLightTurn);
				if(withStep) {
					System.out.println(i);
					board.displayBoard();
				}
				if (withTurns) {
					isLightTurn = !isLightTurn;
				}
			} catch (InvalidCommandException e) {
				System.out.println("Invalid Command: " + e.getMessage());
			}
		}
	}
	
	private void setup() {
		System.out.println("New Game");
		board = new ChessBoard();
		isLightTurn = true;
		executeFile(f.parseFile(new File("scripts/setup")), false, false);
	}

	public void playFile() {
		setup();
		board.displayBoard();
		executeFile(f.parseFile(new File("scripts/test")), true, true);
	}
	
	public void playConsole() {
		setup();
		board.displayBoard();
		
		while(!board.isCheckMate()) {
			IExecutable move = displayMovablePieces(isLightTurn);
			move.executeLite(board, isLightTurn);
			System.out.println(move);
			board.displayBoard();
			isLightTurn = !isLightTurn;
		}
	}
	
	public IExecutable displayMovablePieces(boolean color) {
		ArrayList<Location> movablePieces = board.getPiecesWithValidMoves(isLightTurn);
		IExecutable choice = null;
		
		while(choice == null) {
			System.out.println("Select a Piece to move");
			for(int i = 0; i < movablePieces.size(); i++) {
				System.out.println((i+1) + ". " + board.getPieceAt(movablePieces.get(i)).toTextString() +
						" at " + movablePieces.get(i));
			}
			choice = selectMoveForPiece(movablePieces.get(m.promptIntInRange("", 0, movablePieces.size() + 1)-1));
		}
		
		return choice;
	}
	
	public IExecutable selectMoveForPiece(Location pieceLocation) {
		ArrayList<IExecutable> validCommands = new ArrayList<IExecutable>();
		ArrayList<Location> pieceMoves = board.mateFilter(pieceLocation, board.getPieceAt(pieceLocation).getValidMoves(pieceLocation, board));
		
		for(Location endLocation : pieceMoves) {
			validCommands.add(new MoveCommand(pieceLocation,endLocation,false));
		}
		if(board.getPieceAt(pieceLocation) instanceof King) {
			if(board.isShortCastleClear(pieceLocation)) {
				validCommands.add(new CastleCommand(
						new MoveCommand(pieceLocation, new Location(6,pieceLocation.getRowIndex()), false),
						new MoveCommand(new Location(7, pieceLocation.getRowIndex()), new Location(5, pieceLocation.getRowIndex()), false))
						);
			}
			if(board.isLongCastleClear(pieceLocation)) {
				validCommands.add(new CastleCommand(
						new MoveCommand(pieceLocation, new Location(2,pieceLocation.getRowIndex()), false),
						new MoveCommand(new Location(0, pieceLocation.getRowIndex()), new Location(3, pieceLocation.getRowIndex()), false))
						);
			}
		}
		
		System.out.println("Select a move");
		for(int i = 0; i < validCommands.size(); i++) {
			System.out.println((i+1) + ". " + validCommands.get(i).getSelectString());
		}
		System.out.println(validCommands.size()+1 + ". Back");
		
		int selection = m.promptIntInRange("", 0, validCommands.size()+1);
		IExecutable choice = null;
		if(selection < validCommands.size()+1) {
			choice = validCommands.get(selection-1); 
		}
		
		return choice;
	}
}
