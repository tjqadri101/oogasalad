package play;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import stage.Game;

import controller.DataController;
import engine.GameEngine;

public class EnginePanel extends JPanel {
	
	private DataController dc;
	private GameEngine gameEngine;
	private Game game;
	
	public EnginePanel(DataController dc) {
		this.dc = dc;
		game = new Game();
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(600,800));
		gameEngine = dc.initGameEngine(game);
		add(gameEngine, BorderLayout.CENTER);
		setVisible(true);
	}

}