package chess.gui;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

import chess.model.board.ChessBoard;
import chess.model.board.Location;
import chess.model.pieces.ChessPiece;

public class ChessPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<ChessSpace> spaces = new ArrayList<ChessSpace>();
	private ChessSpace firstSelect;
	private ChessSpace secondSelect;
	
	ChessBoard board;
	

	public ChessPanel(MouseController mc, ChessBoard board) {
		this.board = board;
		this.addMouseListener(mc);
		this.addMouseMotionListener(mc);
		firstSelect = null;
		secondSelect = null;
		
		createSpaces();
		for(ChessSpace c: spaces) {
			mc.addObserver(c);
		}
	}
	
	protected void paintComponent(Graphics g) {
		g.clearRect(0, 0, 800, 800);
		for (ChessSpace c : spaces) {
			c.drawMe(g);
		}
		for(ChessSpace c : spaces) {
			c.drawPiece(g);
		}
		for(ChessSpace c : spaces) {
			c.drawLight(g);
		}
	}
	
	private void createSpaces() {
		for (int i = 0; i <= ChessBoard.MAX_INDEX; i++) {
			for (int j = 0; j <= ChessBoard.MAX_INDEX; j++) {
				spaces.add(new ChessSpace(i, j, ((i+1+j+1)%2 != 0)? true:false));
			}
		}
	}
	
	public void updateBoard(ChessBoard board) {
		for(ChessSpace c: spaces) {
			ChessPiece p = board.getPieceAt(new Location(c.getX(), c.getY()));
			if(p == null) {
				c.setPiece(null);
			}
			else {
				c.setPiece(PieceImageFactory.getImage(p.getPieceType(), p.isLight()));
			}
		}
		this.repaint();
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
	
	public void resetSelected() {
		for(ChessSpace c : spaces) {
			c.setSelected(false);
		}
	}
	
	public void lightAvailableMoves(ArrayList<Location> list) {
		for(ChessSpace c : spaces) {
			Location l = c.toLocation();
			if(list.contains(l)) {
				c.setSelected(true);
			}
		}
	}
}
