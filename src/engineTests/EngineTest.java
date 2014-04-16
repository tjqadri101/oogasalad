package engineTests;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import objects.GameObject;
import objects.NonPlayer;
import objects.Player;

import org.jbox2d.dynamics.Body;

import stage.Game;
import engine.GameEngine;

public class EngineTest {
	public static void main(String[] arg){
		JFrame mainFrame = new JFrame("test");
		GameEngine engine = new GameEngine();
		Game game = new Game();
		engine.setGame(game);
		JPanel panel = new JPanel();
		panel.add(engine);
		mainFrame.add(panel, BorderLayout.CENTER);
		mainFrame.pack();
		mainFrame.setVisible(true);
		System.out.print("EngineTest starts");
		game.addLevel(1);
		game.addScene(1, 0);
		engine.setCurrentScene(1, 0);
		//NonPlayer actor = engine.createActor(0, "actor_default.png", 500, 100, null, 0, 1);
		
		//actor.setMoveBehavior("RegularMove", -5, 5);
		Player player = engine.createPlayer(0, "actor_default.png", 500, 100, null, 0, 1);
		//engine.createBackground("actor_default.png");
    }
}