package play;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import controller.DataController;

import stage.Game;

import engine.GameEngine;

/**
 * 
 * @author David Chou
 *
 */




/*
 * This class stores all of the different components of the Engine into one component.
 * It also ensures that they can interact with each other. The current components are:
 * 		Controller (inserted into the JPanel)
 * 		MenuBar
 * 			File
 * 				Open
 * 				Quit
 */
public class EngineFrame extends JFrame{

	private JPanel myPanel;
	private JMenuBar myMenuBar;

	private DataController myController;
	
	
	public EngineFrame() {
		myController = new DataController();
		setPreferredSize(new Dimension(600, 800));
		setLayout(new BorderLayout());
		
		
		myMenuBar = new PlayMenuBar(myController);
		setJMenuBar(myMenuBar);
		
		myPanel = new EnginePanel(myController);
		add(myPanel, BorderLayout.CENTER);
		
		pack();
		setVisible(true);
	}
	
}
