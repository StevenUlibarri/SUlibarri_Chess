package chess.gui;

import java.awt.event.MouseEvent;
import java.util.Observable;

import javax.swing.event.MouseInputListener;

public class MouseController extends Observable implements MouseInputListener {

	public void mouseClicked(MouseEvent e) {
		setChanged();
		notifyObservers(e);
	}
	
	public void mouseMoved(MouseEvent e) {
		setChanged();
		notifyObservers(e);
	}
	
	public void mouseExited(MouseEvent arg0) {}
	public void mousePressed(MouseEvent arg0) {}
	public void mouseReleased(MouseEvent arg0) {}
	public void mouseDragged(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent arg0) {}

}
