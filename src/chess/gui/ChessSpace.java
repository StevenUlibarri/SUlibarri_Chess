package chess.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import chess.model.board.Location;
import chess.model.pieces.ChessPiece;

public class ChessSpace implements Observer {

	public static final int SIZE = 100;
	public static final Color litColor = Color.RED;
	
	private int x,y, xPos, yPos;
	private ChessPiece chessPiece;
	private Color baseColor;
	private boolean highLighted = false;
	private boolean selected = false;
	private BufferedImage pieceImage = null;
	
	
	public ChessSpace(int x, int y, boolean color, ChessPiece chessPiece) {
		this.x = x;
		this.y = y;
		this.xPos = x*100;
		this.yPos = (y*-100) + 700;
		this.chessPiece = chessPiece;
		updateImage();
		
		baseColor = (!color)? new Color(204,228,242) : new Color(60,84,99);
	}
	
	public void updateImage() {
		if(chessPiece != null) {
			this.pieceImage = PieceImageFactory.getImage(this.chessPiece.getPieceType(), this.chessPiece.isLight());
		}
		else {
			this.pieceImage = null;
		}
	}
	
	public void drawMe(Graphics g) {
		g.setColor(baseColor);
		g.fillRect(xPos, yPos, SIZE, SIZE);
	}
	
	public void drawLight(Graphics g) {
		if(highLighted) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(Color.red);
		    g2.setStroke(new BasicStroke(3));
			g2.drawRect(xPos+2, yPos+2, SIZE-5, SIZE-5);
		}
		if(selected) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(Color.green);
		    g2.setStroke(new BasicStroke(3));
			g2.drawRect(xPos+5, yPos+5, SIZE-12, SIZE-12);
		}
	}
	
	public void drawPiece(Graphics g) {
		if(pieceImage != null) {
			g.drawImage(pieceImage, xPos+25, yPos+25, 50, 50, null);
		}
	}
	
	@Override
	public void update(Observable obs, Object obj) {
		
		if(obs instanceof MouseController && obj instanceof MouseEvent) {
			MouseEvent e = (MouseEvent)obj;
			
			if(this.contains(e.getPoint()) && !highLighted) {
				highLighted = true;
				e.getComponent().repaint();
			}
			else if (!this.contains(e.getPoint()) && highLighted) {
				highLighted = false;
				e.getComponent().repaint();
			}
		}
	}

	boolean contains(Point point) {
		return (point.x > xPos && point.x < xPos + SIZE && point.y > yPos && point.y < yPos + SIZE);
	}
	
	public void setPiece(ChessPiece piece) {
		this.chessPiece = piece;
	}
	
	public ChessPiece getPiece() {
		return this.chessPiece;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}

	public void setSelected(boolean b) {
		this.selected = b;
	}
	
	public void setHighLighted(boolean b) {
		this.highLighted = b;
	}
	
	public Location toLocation() {
		return new Location(x,y);
	}
	
	public String toString() {
		return this.toLocation().toString();
	}
}
