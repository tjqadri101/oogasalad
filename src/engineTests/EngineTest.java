package engineTests;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import objects.GameObject;
import objects.Player;

import stage.Game;
import engine.GameEngine;

public class EngineTest {
	public static void main(String[] arg){
		JFrame mainFrame = new JFrame("test");
		GameEngine myEngine = new GameEngine();
		Game g = new Game();
		myEngine.setGame(g);
		JPanel jp = new JPanel();
		jp.add(myEngine);
		mainFrame.add(jp, BorderLayout.CENTER);
		mainFrame.pack();
		mainFrame.setVisible(true);
		System.out.print("lol\n");
		g.addLevel(1);
		g.addScene(1, 0);
		myEngine.setCurrentScene(0);
		myEngine.setCurrentLevel(1);
		myEngine.createPlayer(0, "actor_default.png", 200, 200, "hero", 0);
		myEngine.createActor(1, "actor_default.png", 100, 100, "heroC", 1);
		GameObject o = g.getGameObject(1, 0, 1);
		Player p = g.getPlayer();
		p.setKey('W', "moveUp");
		o.setMoveBehavior("RegularMove", 3, 3);
//		o.setForce(10, 10);
//		ge.createBackground("actor_default.png");
    }
}