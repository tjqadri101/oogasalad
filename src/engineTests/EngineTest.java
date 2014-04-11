package engineTests;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import engine.GameEngine;

public class EngineTest {
	public static void main(String[] arg){
		JFrame mainFrame = new JFrame("test");
		GameEngine ge = new GameEngine();
		JPanel jp = new JPanel();
		jp.add(ge);
		mainFrame.add(jp, BorderLayout.CENTER);
		mainFrame.pack();
		mainFrame.setVisible(true);
		
		System.out.print("lol\n");
		ge.createPlayer(0, "actor_default.png", 0, 0, null, 0);
		ge.createBackground("actor_default.png");
    }
}