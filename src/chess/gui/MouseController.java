package chess.gui;

import java.awt.event.MouseEvent;
import java.util.Observable;

import javax.swing.event.MouseInputListener;

import chess.model.commands.IExecutable;

public class MouseController extends Observable implements MouseInputListener {

	public void mouseClicked(MouseEvent e) {
		ChessPanel p = (ChessPanel) e.getComponent();
		
		if(p.getFirstSelect() == null) {
			ChessSpace s = p.getClicked(e.getPoint());
			if(s.getPiece() != null && s.getPiece().isLight() == p.getBoard().getTurn()) {
				p.setFirstSelect(s);
				s.setSelected(true);
				setChanged();
				notifyObservers(e);
				p.repaint();
			}
		}
		else if(p.getFirstSelect() != null && p.getSecondSelect() == null) {
			p.setSecondSelect(p.getClicked(e.getPoint()));
			IExecutable ex = p.getValidMove();
			if(ex != null) {
				setChanged();
				notifyObservers(ex);
			}
			else {
				p.setFirstSelect(null);
				p.setSecondSelect(null);
				p.clearSelected();
				p.repaint();
			}
			
			
		}
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
