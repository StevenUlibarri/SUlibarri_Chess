package chess.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

public class ChessSpace implements Observer {

	public static final int SIZE = 100;
	public static final Color litColor = Color.RED;
	
	int x,y;
	Color baseColor;
	boolean highLighted = false;
	boolean selected = false;
	
	
	public ChessSpace(int x, int y, boolean color) {
		this.x = x;
		this.y = y;
		baseColor = (color)? Color.white : Color.black;
	}
	
	public void drawMe(Graphics g) {
		g.setColor(baseColor);
		g.fillRect(x, y, SIZE, SIZE);
		
		if(selected || highLighted) {
			
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(litColor);
		    g2.setStroke(new BasicStroke(3));
			
			g2.drawRect(x, y, SIZE, SIZE);
		}
	}
	
	@Override
	public void update(Observable obs, Object obj) {
		
		if(obs instanceof MouseController && obj instanceof MouseEvent) {
			MouseEvent e = (MouseEvent)obj;
			
			if(this.contains(e.getPoint())) {
				highLighted = true;
				e.getComponent().repaint();
			}
		}
		
	}

	private boolean contains(Point point) {
		return (point.x > x && point.x < x + SIZE && point.y > y && point.y < y + SIZE);
	}

}
