package play;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import controller.DataController;

import stage.Game;

import engine.GameEngine;

public class EngineFrame extends JFrame{

	private JPanel myPanel;
	private JMenuBar myMenuBar;

	private DataController myController;
	private GameEngine myEngine;
	
	
	public EngineFrame() {
		myController = new DataController();
		setPreferredSize(new Dimension(600, 800));
		setLayout(new BorderLayout());
		
		
		myMenuBar = new PlayMenuBar(myController);
		setJMenuBar(myMenuBar);
		
		myEngine = new GameEngine();
		myPanel = new EnginePanel(myController);
		add(myPanel, BorderLayout.CENTER);
		
		pack();
		setVisible(true);
	}
	
}
