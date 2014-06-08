package chess.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.SwingUtilities;

public class ChessSpace implements Observer {

	public static final int SIZE = 100;
	public static final Color litColor = Color.RED;
	
	int x,y;
	Color baseColor;
	boolean highLighted = false;
	boolean selected = false;
	Image piece;
	
	
	public ChessSpace(int x, int y, boolean color) {
		this.x = x;
		this.y = y;
		baseColor = (!color)? new Color(204,228,242) : new Color(60,84,99);
	}
	
	public void drawMe(Graphics g) {
		g.setColor(baseColor);
		g.fillRect(x*100, (y*-100)+700, SIZE, SIZE);
	}
	
	public void drawLight(Graphics g) {
		if(highLighted || selected) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor((selected)? Color.green:Color.red);
		    g2.setStroke(new BasicStroke(3));
			g2.drawRect(x*100+2, (y*-100)+700+2, SIZE-5, SIZE-5);
		}
	}
	
	@Override
	public void update(Observable obs, Object obj) {
		
		if(obs instanceof MouseController && obj instanceof MouseEvent) {
			MouseEvent e = (MouseEvent)obj;
			ChessPanel p = (ChessPanel) e.getComponent();
			
			if(this.contains(e.getPoint()) && SwingUtilities.isLeftMouseButton(e)) {
				selected = true;
				e.getComponent().repaint();
			}
			else if(this.contains(e.getPoint()) && !highLighted) {
				highLighted = true;
				e.getComponent().repaint();
			}
			else if (!this.contains(e.getPoint()) && highLighted) {
				highLighted = false;
				e.getComponent().repaint();
			}
		}
	}

	private boolean contains(Point point) {
		return (point.x > x*100 && point.x < x*100 + SIZE && point.y > (y*-100)+700 && point.y < (y*-100)+700 + SIZE);
	}

}
