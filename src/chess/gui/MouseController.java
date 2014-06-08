package chess.gui;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Observable;

import javax.swing.event.MouseInputListener;

public class MouseController extends Observable implements MouseInputListener {
	
	private Point currentPoint;
	
	public MouseController() {
		currentPoint = new Point(0,0);
	}
	
	private void setPoint(Point newPoint) {
		currentPoint = newPoint;
	}

	public void mouseClicked(MouseEvent e) {
		setPoint(e.getPoint());
		setChanged();
		notifyObservers(e);
		System.out.println("Click");
	}
	
	public void mouseMoved(MouseEvent e) {
		setPoint(e.getPoint());
		setChanged();
		notifyObservers(e);
	}
	
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	public void mouseDragged(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}

}
