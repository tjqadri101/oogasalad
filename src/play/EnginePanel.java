package play;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import stage.Game;

import controller.DataController;
import engine.GameEngine;

/**
 * 
 * @author David Chou
 *
 */


/*
 * This panel only stores the DataController, which holds the GameEngine. The GameEngine
 * holds the Game. [DataController --> GameEngine --> Game]
 */
public class EnginePanel extends JPanel {
	
	private DataController dc;
	private GameEngine gameEngine;
	private Game game;
	
	private static final boolean IS_EDITING = false;
	
	public EnginePanel(DataController dc) {
		this.dc = dc;
		game = new Game();
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(600,800));
		gameEngine = dc.initGameEngine(IS_EDITING);
		add(gameEngine, BorderLayout.CENTER);
		setVisible(true);
	}
	
	public GameEngine getEngine() {
		return gameEngine;
	}

}