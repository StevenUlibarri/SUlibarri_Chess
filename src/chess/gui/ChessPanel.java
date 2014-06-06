package chess.gui;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class ChessPanel extends JPanel implements Observer {
	
	public ChessPanel(MouseController mc) {
		this.addMouseListener(mc);
		this.addMouseMotionListener(mc);
		
	}
	
	protected void paintComponent(Graphics g) {
		
	}
	
	private void createSpaces() {
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
