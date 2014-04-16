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
		game.addLevel(1);
		game.addScene(1, 0);
		engine.setCurrentScene(1, 0);
		NonPlayer actor = engine.createActor(0, "actor_default.png", 100.0, 500.0, null, 0, 1);
		//actor.setCollisionBehavior("HitterEliminateVictim", 0);
		//engine.setTiles();
		//actor.setMoveBehavior("RegularMove", -5, 5);
		Player player = engine.createPlayer(0, "actor_default.png", 500, 100, null, 0, 1);
		engine.addCollisionPair(0, "HitterEliminateVictim", 0);
		player.setKey('W', "moveUp");
		player.setKey('S', "moveDown");
		player.setKey('A', "moveLeft");
		player.setKey('D', "moveRight");
		player.setSpeed(3.0);
		//engine.createBackground("actor_default.png");
    }
}