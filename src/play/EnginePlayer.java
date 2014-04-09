package play;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import controller.DataController;

import stage.Game;

import engine.GameEngine;

public class EnginePlayer extends JFrame{

	private JPanel myPanel;
	private JMenuBar myMenuBar;

	private DataController myController;
	private GameEngine myEngine;
	private Game myGame;
	
	public EnginePlayer() {
		myController = new DataController();
		myMenuBar = new PlayMenuBar(myController);
		setJMenuBar(myMenuBar);
		myGame = new Game();
		
		myPanel = new JPanel();
		myPanel.add(new GameEngine(myGame));
		myPanel.setLayout(new BorderLayout());
//		myPanel.add(GameEngine engine, BorderLayout.CENTER);
		
		setMinimumSize(new Dimension(600, 800));
		setVisible(true);
	}
	
}
