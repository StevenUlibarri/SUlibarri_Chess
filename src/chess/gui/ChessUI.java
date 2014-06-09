package chess.gui;

import javax.swing.JFrame;

import chess.controller.Controller;

public class ChessUI {
	
	private ChessPanel cp;
	private MouseController mc;
	
	public ChessUI(Controller c) {
		JFrame mainFrame = new JFrame("Chess Yo");
		mainFrame.setSize(804, 828);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mc = new MouseController();
		mc.addObserver(c);
		cp = new ChessPanel(mc, c.getBoard());
		cp.setVisible(true);
		
		mainFrame.add(cp);
		mainFrame.setVisible(true);
	}
	
	public ChessPanel getPanel() {
		return cp;
	}
}
