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

	private EnginePanel myPanel;
	private JMenuBar myMenuBar;

	private DataController myController;
	
	
	public EngineFrame() {
		myController = new DataController();
		initializeViewSpecs();
		addPanel();
		addMenu();
		finalizeView();
	}


	private void initializeViewSpecs() {
		setPreferredSize(new Dimension(800, 600));
		setLayout(new BorderLayout());
	}


	private void addPanel() {
		myPanel = new EnginePanel(myController);
		add(myPanel, BorderLayout.CENTER);
	}


	private void addMenu() {
		myMenuBar = new PlayMenuBar(myController, myPanel.getEngine());
		setJMenuBar(myMenuBar);
	}


	private void finalizeView() {
		pack();
		setVisible(true);
	}
	
}
