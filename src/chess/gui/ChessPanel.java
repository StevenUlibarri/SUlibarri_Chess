package chess.gui;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

import chess.model.board.ChessBoard;
import chess.model.board.Location;
import chess.model.commands.IExecutable;

public class ChessPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private ChessSpace[][] spaces = new ChessSpace[8][8];
	private ChessSpace firstSelect;
	private ChessSpace secondSelect;
	private ArrayList<IExecutable> currentValidCommands;
	
	ChessBoard board;
	

	public ChessPanel(MouseController mc, ChessBoard board) {
		this.board = board;
		this.addMouseListener(mc);
		this.addMouseMotionListener(mc);
		firstSelect = null;
		secondSelect = null;
		
		createSpaces(mc);
	}
	
	protected void paintComponent(Graphics g) {
		g.clearRect(0, 0, 800, 800);
		for (int i = 0; i <= ChessBoard.MAX_INDEX; i++) {
			for (int j = 0; j <= ChessBoard.MAX_INDEX; j++) {
				spaces[i][j].drawMe(g);
			}
		}
		for (int i = 0; i <= ChessBoard.MAX_INDEX; i++) {
			for (int j = 0; j <= ChessBoard.MAX_INDEX; j++) {
				spaces[i][j].drawPiece(g);
			}
		}
		for (int i = 0; i <= ChessBoard.MAX_INDEX; i++) {
			for (int j = 0; j <= ChessBoard.MAX_INDEX; j++) {
				spaces[i][j].drawLight(g);
			}
		}
	}
	
	private void createSpaces(MouseController mc) {
		for (int i = 0; i <= ChessBoard.MAX_INDEX; i++) {
			for (int j = 0; j <= ChessBoard.MAX_INDEX; j++) {
				spaces[i][j] = new ChessSpace(i, j, ((i+1+j+1)%2 != 0)? true:false, board.getPieceArray()[i][j]);
				mc.addObserver(spaces[i][j]);
			}
		}
	}
	
	public void setFirstSelect(ChessSpace space) {
		this.firstSelect = space;
	}
	
	public ChessSpace getFirstSelect() {
		return this.firstSelect;
	}
	
	public void setSecondSelect(ChessSpace space) {
		this.secondSelect = space;
	}
	
	public ChessSpace getSecondSelect() {
		return this.secondSelect;
	}
	
	public ChessBoard getBoard() {
		return this.board;
	}
	public void resetSelected() {
		for (int i = 0; i <= ChessBoard.MAX_INDEX; i++) {
			for (int j = 0; j <= ChessBoard.MAX_INDEX; j++) {
				spaces[i][j].setSelected(false);
			}
		}
	}
	
	public void lightValidMoves(ArrayList<IExecutable> commands) {
		ArrayList<Location> loc = new ArrayList<Location>();
		for(IExecutable e: commands) {
			loc.add(e.getDestination());
		}
		
		for (int i = 0; i <= ChessBoard.MAX_INDEX; i++) {
			for (int j = 0; j <= ChessBoard.MAX_INDEX; j++) {
				if(loc.contains(spaces[i][j].toLocation())) {
					spaces[i][j].setSelected(true);
				}
			}
		}
		this.repaint();
		
		currentValidCommands = commands;
	}
	
	public void updateImages() {
		for (int i = 0; i <= ChessBoard.MAX_INDEX; i++) {
			for (int j = 0; j <= ChessBoard.MAX_INDEX; j++) {
				spaces[i][j].setPiece(board.getPieceArray()[i][j]);
				spaces[i][j].updateImage();
			}
		}
	}
	
	public IExecutable getValidMove() {
		IExecutable move = null;
		for(IExecutable e: currentValidCommands) {
			if(e.getDestination().equals(secondSelect.toLocation())) {
				move = e;
			}
		}
		return move;
	}
	
	public void clearSelected() {
		for (int i = 0; i <= ChessBoard.MAX_INDEX; i++) {
			for (int j = 0; j <= ChessBoard.MAX_INDEX; j++) {
				spaces[i][j].setSelected(false);
			}
		}
	}
	
	public ChessSpace getClicked(Point p) {
		for (int i = 0; i <= ChessBoard.MAX_INDEX; i++) {
			for (int j = 0; j <= ChessBoard.MAX_INDEX; j++) {
				if(spaces[i][j].contains(p)) {
					return spaces[i][j];
				}
			}
		}
		return null;
	}
}
