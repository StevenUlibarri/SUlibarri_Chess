package chess.controller;

import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.SwingUtilities;

import chess.gui.ChessPanel;
import chess.gui.ChessUI;
import chess.gui.MouseController;
import chess.model.board.ChessBoard;
import chess.model.board.Location;
import chess.model.commands.CastleCommand;
import chess.model.commands.IExecutable;
import chess.model.commands.MoveCommand;
import chess.model.customExceptions.InvalidCommandException;
import chess.model.pieces.King;

public class Controller implements Observer {

	private ChessBoard board;
	private FileIO f;
	private MyIO m;
	private ChessUI c;

	
	public Controller() {
		f = new FileIO();
		m = new MyIO();
		
	}
	
	private void executeFile(ArrayDeque<IExecutable> list, boolean withStep, boolean withTurns) {
		while (!list.isEmpty()) {
			try {
				IExecutable i = list.poll();
				i.execute(board);
				if(withStep) {
					System.out.println(i);
					board.displayBoard();
				}
				if (withTurns) {
					board.swapTurn();
				}
			} catch (InvalidCommandException e) {
				System.out.println("Invalid Command: " + e.getMessage());
			}
		}
	}
	
	private void setup() {
		System.out.println("New Game");
		board = new ChessBoard();
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
		
		while(!board.isMate()) {
			IExecutable move = displayMovablePieces(board.getTurn());
			move.executeLite(board);
			System.out.println(move);
			board.displayBoard();
			board.swapTurn();
		}
	}
	
	public IExecutable displayMovablePieces(boolean color) {
		ArrayList<Location> movablePieces = board.getPiecesWithValidMoves(board.getTurn());
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
		ArrayList<IExecutable>validCommands = getValidMovesForPiece(pieceLocation);
		
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
	
	public void playGui() {
		setup();
		c = new ChessUI(this);
	}

	@Override
	public void update(Observable obs, Object obj) {
		if(obs instanceof MouseController && obj instanceof MouseEvent) {
			MouseEvent e = (MouseEvent) obj;
			ChessPanel p = (ChessPanel) e.getComponent();
			
			if(SwingUtilities.isLeftMouseButton(e) && p.getFirstSelect() != null && p.getSecondSelect() == null) {
				ArrayList<IExecutable> moves = getValidMovesForPiece(p.getFirstSelect().toLocation());
				c.getPanel().lightValidMoves(moves);
			}
		}
		if(obs instanceof MouseController && obj instanceof IExecutable) {
			IExecutable e = (IExecutable) obj;
			e.executeLite(board);
			board.swapTurn();
			System.out.println(e);
			c.getPanel().setFirstSelect(null);
			c.getPanel().setSecondSelect(null);
			c.getPanel().clearSelected();
			c.getPanel().updateImages();
			c.getPanel().repaint();
		}
	}
	
	public ChessBoard getBoard() {
		return this.board;
	}
	
	private ArrayList<IExecutable>getValidMovesForPiece(Location pieceLocation) {
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
		return validCommands;
	}
}
