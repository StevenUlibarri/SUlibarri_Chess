package chess.gui;

import javax.swing.JFrame;

import chess.controller.Controller;

public class ChessUI {
	
	private ChessPanel cp;
	
	public ChessUI(Controller c) {
		JFrame mainFrame = new JFrame("Chess Yo");
		mainFrame.setSize(806, 830);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		cp = new ChessPanel(new MouseController());
		cp.setVisible(true);
		
		mainFrame.add(cp);
		mainFrame.setVisible(true);
	}
	
	public ChessPanel getPanel() {
		return cp;
	}
}
