package play;

import java.awt.Dimension;
import java.awt.MenuBar;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import stage.Game;

import engine.GameEngine;

import generic_feature_gui.Feature;

/**
 * 
 * @author David Chou
 *
 */

public class EngineFeature extends Feature{

	private MenuBar myMenu;
	private JFrame myFrame;
	private Game myGame;
	
	public EngineFeature () {
		myMenu = new MenuBar();
		myFrame = new JFrame("Game Player");
		myFrame.add(new GameEngine());
		myFrame.pack();
		myFrame.setVisible(true);
		myFrame.setMinimumSize(new Dimension(600,800));
	}
	
	
	
}
