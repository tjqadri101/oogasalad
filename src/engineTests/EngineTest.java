package engineTests;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jbox2d.dynamics.Body;

import stage.Game;

import engine.GameEngine;

public class EngineTest {
	public static void main(String[] arg){
		JFrame mainFrame = new JFrame("test");
		GameEngine ge = new GameEngine();
		Game g = new Game();
		ge.setGame(g);
		JPanel jp = new JPanel();
		jp.add(ge);
		mainFrame.add(jp, BorderLayout.CENTER);
		mainFrame.pack();
		mainFrame.setVisible(true);
		System.out.print("lol\n");
		ge.createPlayer(0, "actor_default.png", 200, 200, "hero", 0);
		g.getPlayer().setKey('w', "moveUp");
		g.getPlayer().setKey('W', "moveUp");
		//ge.createBackground("actor_default.png");
    }
}